package com.seblacity.firebase.adapters.items

import com.mikepenz.fastadapter.items.AbstractItem
import com.seblacity.firebase.firestore.entity.FirestoreModel

/**
 * Created by Onur on 10.7.2018.
 */
abstract class FirestoreModelItem<M : FirestoreModel, Item : AbstractItem<*, *>, VH : FirebaseViewHolder<Item>>(var model: M) : FirebaseBaseItem<Item, VH>() {
}