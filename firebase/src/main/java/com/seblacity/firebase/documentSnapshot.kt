package com.seblacity.firebase

import com.google.firebase.firestore.DocumentSnapshot
import com.seblacity.firebase.firestore.entity.FirestoreModel
import kotlin.reflect.KClass

/**
 * Created by Onur on 11.8.2018.
 */
fun <F : FirestoreModel> Iterable<DocumentSnapshot>.createModels(clazz: KClass<F>) = map { it.createModel(clazz) }

fun <F : FirestoreModel> DocumentSnapshot.createModel(clazz: KClass<F>) = toObject(clazz.java)?.also {
    it.documentSnapshot = this
    it.documentReference = reference
    it.documentId = id
}