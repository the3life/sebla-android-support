package com.seblacity.firebase.adapters.items

import android.content.Context
import android.view.View
import butterknife.ButterKnife
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by Onur on 10.7.2018.
 */
abstract class FirebaseViewHolder<Item : AbstractItem<*, *>>(itemView: View) : FastAdapter.ViewHolder<Item>(itemView) {
    val context: Context
        get() {
            return itemView.context
        }

    lateinit var item: Item
    var created: Boolean = false
    var binded: Boolean = false

    init {
        ButterKnife.bind(this, itemView)
    }

    override fun unbindView(item: Item) {
        this.binded = false
    }

    override fun bindView(item: Item, payloads: MutableList<Any>?) {
        this.item = item
        this.created = true
        this.binded = false
    }
}