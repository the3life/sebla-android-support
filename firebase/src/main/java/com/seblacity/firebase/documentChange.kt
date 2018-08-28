package com.seblacity.firebase

import com.google.firebase.firestore.DocumentChange
import com.seblacity.firebase.firestore.entity.FirestoreModel
import kotlin.reflect.KClass

/**
 * Created by Onur on 17.8.2018.
 */
fun <F : FirestoreModel> Iterable<DocumentChange>.createModels(clazz: KClass<F>) = map { it.createModel(clazz) }

fun <F : FirestoreModel> DocumentChange.createModel(clazz: KClass<F>) = document.toObject(clazz.java).also {
    it.documentChange = this
    it.documentSnapshot = document
    it.documentReference = document.reference
    it.documentId = document.id
}