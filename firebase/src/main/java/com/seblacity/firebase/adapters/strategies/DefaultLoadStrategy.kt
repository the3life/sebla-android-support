package com.seblacity.firebase.adapters.strategies

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
 * Created by Onur on 13.8.2018.
 */
abstract class DefaultLoadStrategy<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> : BaseLoadStrategy<M, Item>() {
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
                    querySnapshot.documents.toFlowable()
                            .map { onCreateModel(it)!! }
                            .toList()
                            .observeOn(Schedulers.io())
                            .flatMap { models ->
                                models.toFlowable()
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
                            .flattenAsFlowable { it }
                            .map { onCreateItem(it).withIdentifier(it.longId) as Item }
                            .toList()
                            .doOnSuccess { adapter.setNewList(it) }
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