package com.seblacity.firebase.adapters.strategies

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.seblacity.firebase.adapters.FirebaseRecyclerViewAdapter
import com.seblacity.firebase.adapters.items.FirestoreModelItem
import com.seblacity.firebase.annotations.SubQuery
import com.seblacity.firebase.firestore.entity.FirestoreModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 26.8.2018.
 */
abstract class ModifiableLoadStrategy<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> : DefaultLoadStrategy<M, Item>() {
    private val disposable = CompositeDisposable()

    private lateinit var listenerRegistration: ListenerRegistration

    override fun initialize(adapter: FirebaseRecyclerViewAdapter<M, Item>) {
        super.initialize(adapter)

        load()
    }

    override fun load() {
        super.load()

        listenerRegistration = query(collection()).addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (querySnapshot == null || firebaseFirestoreException != null) {
                firebaseFirestoreException?.printStackTrace()

                onLoadError(firebaseFirestoreException ?: Throwable())

                return@addSnapshotListener
            }

            disposable.add(
                    querySnapshot.documentChanges.toFlowable()
                            .map { documentChange ->
                                val model = onCreateModel(documentChange)

                                return@map documentChange.type to model
                            }
                            .toList()
                            .observeOn(Schedulers.io())
                            .flatMap { models ->
                                models.map { it.second }.toFlowable()
                                        .observeOn(Schedulers.io())
                                        .parallel()
                                        .runOn(Schedulers.io())
                                        .doOnNext { model ->
                                            if (model is SubQuery) {
                                                model.query()
                                            }
                                        }
                                        .sequential()
                                        .ignoreElements()
                                        .toSingleDefault(models)
                            }
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSuccess { models ->
                                models.forEach { model ->
                                    when (model.first) {
                                        DocumentChange.Type.ADDED -> {
                                            val item = onCreateItem(model.second).withIdentifier(model.second.longId) as Item

                                            adapter.add(item)
                                        }
                                        DocumentChange.Type.MODIFIED -> {
                                            val position = adapter.getAdapterPosition(model.second.longId)
                                            val item = onCreateItem(model.second).withIdentifier(model.second.longId) as Item

                                            if (position != -1) {
                                                adapter.set(position, item)
                                            }
                                        }
                                        DocumentChange.Type.REMOVED -> {
                                            val position = adapter.getAdapterPosition(model.second.longId)

                                            if (position != -1) {
                                                adapter.remove(position)
                                            }
                                        }
                                    }
                                }
                            }
                            .doOnSuccess { onLoadComplete() }
                            .doOnError { throwable ->
                                onLoadError(throwable)

                                listenerRegistration.remove()
                            }
                            .subscribe(
                                    { },
                                    { throwable ->
                                        throwable.printStackTrace()

                                        onLoadError(throwable)
                                    })
            )
        }
    }

    override fun destroy() {
        disposable.clear()
        adapter.clear()
    }
}