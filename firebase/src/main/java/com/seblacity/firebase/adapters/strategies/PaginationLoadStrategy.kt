package com.seblacity.firebase.adapters.strategies

import android.os.Handler
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import com.seblacity.firebase.adapters.FirebaseRecyclerViewAdapter
import com.seblacity.firebase.adapters.items.FirestoreModelItem
import com.seblacity.firebase.firestore.entity.FirestoreModel
import com.seblacity.firebase.toChangesFlowable
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 13.8.2018.
 */
abstract class PaginationLoadStrategy<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> : BaseLoadStrategy<M, Item>() {
    private val disposable = CompositeDisposable()
    private val handler = Handler()

    private var scrollListener: EndlessRecyclerOnScrollListener? = null
    private var lastDocument: DocumentSnapshot? = null

    private var page = -1
    private var lastPage = false

    override fun initialize(adapter: FirebaseRecyclerViewAdapter<M, Item>) {
        super.initialize(adapter)

        load()
    }

    override fun load() {
        super.load()

        page++

        if (scrollListener == null) {
            scrollListener = object : EndlessRecyclerOnScrollListener() {
                override fun onLoadMore(currentPage: Int) {
                    if (!loading && !lastPage) {
                        handler.post { load() }
                    }
                }
            }

            adapter.recyclerView.addOnScrollListener(scrollListener)
        }

        /*paginationQuery(query(collection())).toChangesFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .map { onCreateModels(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { models ->
                    if (models.isEmpty()) {
                        lastPage = true
                    } else {
                        lastDocument = models.last().second.documentSnapshot
                    }

                    models.forEach { data ->
                        when (data.first) {
                            DocumentChange.Type.ADDED -> {
                                val item = onCreateItem(data.second).withIdentifier(data.second.longId) as Item

                                adapter.add(item)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                val position = adapter.getAdapterPosition(data.second.longId)
                                val item = onCreateItem(data.second).withIdentifier(data.second.longId) as Item

                                if (position != -1) {
                                    adapter.set(position, item)
                                }
                            }
                            DocumentChange.Type.REMOVED -> {
                                val position = adapter.getAdapterPosition(data.second.longId)

                                if (position != -1) {
                                    adapter.remove(position)
                                }
                            }
                        }
                    }
                }
                .doAfterNext { models ->
                    if (models.isNotEmpty()) {
                        //onLoadComplete(page, lastPage, models.size)
                    }
                }
                .doOnError { throwable ->
                    throwable.printStackTrace()

                    onLoadError(throwable)
                }
                .subscribe()*/
    }

    override fun destroy() {
        scrollListener?.disable()

        scrollListener = null
        adapter.recyclerView.clearOnScrollListeners()

        disposable.clear()
        adapter.clear()

        lastDocument = null
        lastPage = false
        page = -1
    }

    private fun paginationQuery(query: Query): Query {
        var newQuery = query.limit(10)

        if (lastDocument != null) {
            newQuery = newQuery.startAfter(lastDocument!!)
        }

        return newQuery
    }
}