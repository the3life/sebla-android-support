package com.seblacity.firebase.adapters.strategies

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.seblacity.firebase.adapters.FirebaseRecyclerViewAdapter
import com.seblacity.firebase.adapters.items.FirebaseFooterItem
import com.seblacity.firebase.adapters.items.FirestoreModelItem
import com.seblacity.firebase.firestore.entity.FirestoreModel

/**
 * Created by Onur on 13.8.2018.
 */
interface LoadStrategy<M : FirestoreModel, Item : FirestoreModelItem<M, *, *>> {
    fun initialize(adapter: FirebaseRecyclerViewAdapter<M, Item>)
    fun load()
    fun destroy()

    fun collection(): CollectionReference
    fun query(collection: CollectionReference): Query

    fun onCreateModel(documentChange: DocumentChange): M
    fun onCreateModel(documentSnapshot: DocumentSnapshot): M?
    fun onCreateItem(model: M): Item
    fun onCreateProgressItem(): FirebaseFooterItem<*, *>
    fun onCreateEmptyItem(): FirebaseFooterItem<*, *>?
    fun onCreateErrorItem(): FirebaseFooterItem<*, *>?

    fun onLoading()
    fun onFirstLoadComplete()
    fun onLoadComplete()
    fun onLoadError(throwable: Throwable)
}