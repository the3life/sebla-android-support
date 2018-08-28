package com.seblacity.firebase.adapters.items

import android.view.View
import com.seblacity.firebase.R

/**
 * Created by Onur on 23.8.2018.
 */
class ProgressItem : FirebaseFooterItem<ProgressItem, ProgressItem.ViewHolder>() {
    var size: Size = Size.LARGE

    override fun getType() = R.id.progress_item_id
    override fun getLayoutRes() = when (size) {
        Size.SMALL -> R.layout.layout_progress_item_small
        Size.MEDIUM -> R.layout.layout_progress_item_medium
        Size.LARGE -> R.layout.layout_progress_item_large
    }

    override fun getViewHolder(v: View) = ViewHolder(v)

    fun small() = apply { size = Size.SMALL }

    fun medium() = apply { size = Size.MEDIUM }

    fun large() = apply { size = Size.LARGE }

    enum class Size {
        SMALL, MEDIUM, LARGE
    }

    class ViewHolder(itemView: View) : FirebaseViewHolder<ProgressItem>(itemView)
}