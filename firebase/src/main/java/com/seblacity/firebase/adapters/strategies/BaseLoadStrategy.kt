package com.seblacity.firebase.adapters.strategies

import com.seblacity.firebase.adapters.FirebaseRecyclerViewAdapter
import com.seblacity.firebase.adapters.items.FirebaseFooterItem
import com.seblacity.firebase.adapters.items.FirestoreModelItem
import com.seblacity.firebase.adapters.items.ProgressItem
import com.seblacity.firebase.firestore.entity.FirestoreModel

/**
 * Created by Onur on 13.8.2018.
 */
abstract class BaseLoadStrategy<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> : LoadStrategy<M, Item> {
    protected lateinit var adapter: FirebaseRecyclerViewAdapter<M, Item>
    protected var firstComplete = true
    protected var loading = false

    override fun initialize(adapter: FirebaseRecyclerViewAdapter<M, Item>) {
        this.adapter = adapter
    }

    override fun load() {
        loading = true

        onLoading()

        adapter.footerAdapter.clear()
        adapter.footerAdapter.add(onCreateProgressItem().withEnabled(false))
    }

    override fun onCreateProgressItem(): FirebaseFooterItem<*, *> = ProgressItem()

    override fun onCreateEmptyItem(): FirebaseFooterItem<*, *>? = null

    override fun onCreateErrorItem(): FirebaseFooterItem<*, *>? = null

    override fun onLoading() {
    }

    override fun onFirstLoadComplete() {
    }

    override fun onLoadComplete() {
        loading = false

        adapter.footerAdapter.clear()

        if (firstComplete) {
            firstComplete = false

            onFirstLoadComplete()
        }

        if (adapter.adapterItemCount == 0) {
            val emptyItem = onCreateEmptyItem()

            if (emptyItem != null) {
                adapter.footerAdapter.add(emptyItem.withEnabled(false))
            }
        }
    }

    override fun onLoadError(throwable: Throwable) {
        loading = false

        adapter.clear()
        adapter.footerAdapter.clear()

        val errorItem = onCreateErrorItem()

        if (errorItem != null) {
            adapter.footerAdapter.add(errorItem.withEnabled(false))
        }
    }
}