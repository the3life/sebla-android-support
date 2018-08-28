package com.seblacity.firebase.adapters.items

import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by Onur on 10.7.2018.
 */
abstract class FirebaseBaseItem<Item : AbstractItem<*, *>, VH : FirebaseViewHolder<Item>> : AbstractItem<Item, VH>() {
}

