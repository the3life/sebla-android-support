package com.seblacity.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.seblacity.firebase.annotations.SubQuery
import com.seblacity.firebase.firestore.entity.FirestoreModel
import com.seblacity.rx.google.task.toMaybe
import com.seblacity.rx.google.task.toObservable
import io.reactivex.BackpressureStrategy
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KClass

/**
 * Created by Onur on 12.8.2018.
 */
object Firestore {
    fun collection(collectionPath: String) = FirebaseFirestore.getInstance().collection(collectionPath)

    fun document(documentPath: String) = FirebaseFirestore.getInstance().document(documentPath)

    fun getDocumentById(collectionPath: String?, documentId: String?): Maybe<DocumentSnapshot> {
        return if (collectionPath != null && !collectionPath.isEmpty() && documentId != null && !documentId.isEmpty()) {
            getDocumentById(collection(collectionPath), documentId)
        } else {
            Maybe.empty()
        }
    }

    fun getDocumentById(collection: CollectionReference?, documentId: String?): Maybe<DocumentSnapshot> {
        return if (collection != null && documentId != null && !documentId.isEmpty()) {
            collection.document(documentId).get().toMaybe()
        } else {
            Maybe.empty()
        }
    }

    fun <F : FirestoreModel> getDocumentById(collectionPath: String?, documentId: String?, clazz: KClass<F>): Maybe<F> {
        return if (collectionPath != null && documentId != null && !documentId.isEmpty()) {
            getDocumentById(collection(collectionPath), documentId, clazz)
        } else {
            Maybe.empty()
        }
    }

    fun <F : FirestoreModel> getDocumentById(collection: CollectionReference?, documentId: String?, clazz: KClass<F>): Maybe<F> {
        return if (collection != null && documentId != null && !documentId.isEmpty()) {
            getDocumentById(collection, documentId)
                    .observeOn(Schedulers.io())
                    .map { documentSnapshot -> documentSnapshot.createModel(clazz) }
                    .map { model ->
                        if (model is SubQuery) {
                            model.query()
                        }

                        return@map model
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it }
        } else {
            Maybe.empty()
        }
    }

    fun <F : FirestoreModel> getFirstByQuery(collectionPath: String?, queryFn: (CollectionReference) -> Query, clazz: KClass<F>): Maybe<F> {
        return if (collectionPath != null) {
            return getByQuery(collectionPath, queryFn, clazz).firstElement()
        } else {
            Maybe.empty()
        }
    }

    fun <F : FirestoreModel> getByQuery(collectionPath: String?, queryFn: (CollectionReference) -> Query, clazz: KClass<F>): Observable<F> {
        return if (collectionPath != null) {
            return queryFn.invoke(collection(collectionPath)).get()
                    .toObservable()
                    .map { documentSnapshot -> documentSnapshot.createModel(clazz) }
                    .toFlowable(BackpressureStrategy.BUFFER)
                    .parallel()
                    .runOn(Schedulers.io())
                    .map { model ->
                        if (model is SubQuery) {
                            model.query()
                        }

                        return@map model
                    }
                    .sequential()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            Observable.empty()
        }
    }
}