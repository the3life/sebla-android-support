package com.seblacity.firebase.adapters

import android.support.v7.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISelectionListener
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.EventHook
import com.mikepenz.fastadapter.listeners.OnClickListener
import com.seblacity.firebase.adapters.items.FirestoreModelItem
import com.seblacity.firebase.adapters.strategies.LoadStrategy
import com.seblacity.firebase.firestore.entity.FirestoreModel

/**
 * Created by Onur on 10.7.2018.
 */
class FirebaseRecyclerViewAdapter<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> private constructor(
        val recyclerView: RecyclerView,
        val loadStrategy: LoadStrategy<M, Item>,
        val selectable: Boolean = false,
        val multiSelect: Boolean = false,
        val eventHooks: MutableList<EventHook<AbstractItem<*, *>>>,
        val selectionListener: ISelectionListener<AbstractItem<*, *>>? = null,
        val onClickListener: OnClickListener<AbstractItem<*, *>>? = null) : ItemAdapter<Item>() {

    val footerAdapter: ItemAdapter<AbstractItem<*, *>> = ItemAdapter()
    val adapter: FastAdapter<AbstractItem<*, *>> = FastAdapter.with(listOf(this, footerAdapter))

    fun initialize(): FirebaseRecyclerViewAdapter<M, Item> = apply {
        recyclerView.adapter = adapter

        adapter.withSelectable(selectable)
                .withMultiSelect(multiSelect)
                .withOnClickListener(onClickListener)
                .withSelectionListener(selectionListener)
                .withEventHooks(eventHooks)

        loadStrategy.initialize(this)
    }

    class Builder<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> {
        private lateinit var recyclerView: RecyclerView
        private lateinit var loadStrategy: LoadStrategy<M, Item>
        private var selectable: Boolean = false
        private var multiSelect: Boolean = false
        private val eventHooks: MutableList<EventHook<AbstractItem<*, *>>> = mutableListOf()
        private var onClickListener: OnClickListener<AbstractItem<*, *>>? = null
        private var selectionListener: ISelectionListener<AbstractItem<*, *>>? = null

        fun withRecyclerView(recyclerView: RecyclerView) = apply { this.recyclerView = recyclerView }
        fun withLoadStrategy(loadStrategy: LoadStrategy<M, Item>) = apply { this.loadStrategy = loadStrategy }
        fun withSelectable(selectable: Boolean) = apply { this.selectable = selectable }
        fun withMultiSelect(multiSelect: Boolean) = apply { this.multiSelect = multiSelect }
        fun withEventHooks(eventHooks: ArrayList<EventHook<AbstractItem<*, *>>>) = apply { this.eventHooks.addAll(eventHooks) }
        fun withSelectionListener(selectionListener: ISelectionListener<AbstractItem<*, *>>) = apply { this.selectionListener = selectionListener }
        fun withOnClickListener(onClickListener: OnClickListener<AbstractItem<*, *>>) = apply { this.onClickListener = onClickListener }

        fun build() = FirebaseRecyclerViewAdapter(recyclerView, loadStrategy, selectable, multiSelect, eventHooks, selectionListener, onClickListener)
    }
}