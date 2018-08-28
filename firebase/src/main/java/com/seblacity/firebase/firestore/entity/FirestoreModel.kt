package com.seblacity.firebase.firestore.entity

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

/**
 * Created by Onur on 9.7.2018.
 */
abstract class FirestoreModel {
    companion object {
        /*fun <F : FirestoreModel> create(querySnapshot: QuerySnapshot, clazz: KClass<F>): List<F> {
            return create(querySnapshot.documents, clazz)
        }

        fun <F : FirestoreModel> create(documentSnapshots: List<DocumentSnapshot>, clazz: KClass<F>): List<F> {
            return documentSnapshots.map {
                create(it, clazz)
            }
        }

        fun <F : FirestoreModel> create(documentSnapshot: DocumentSnapshot, clazz: KClass<F>): F {
            //FIXME
            val model: F = documentSnapshot.toObject(clazz.java)!!

            model.documentSnapshot = documentSnapshot
            model.documentReference = documentSnapshot.reference
            model.documentId = documentSnapshot.id
            //model.longId = documentSnapshot.id.hashCode().toLong()

            return model
        }

        fun getByDocumentId(collection: String?, documentId: String?): Maybe<DocumentSnapshot> {
            return if (collection != null && !collection.isEmpty() && documentId != null) {
                getByDocumentId(FirebaseFirestore.getInstance().collection(collection), documentId)
            } else {
                Maybe.empty()
            }
        }

        fun getByDocumentId(collection: CollectionReference?, documentId: String?): Maybe<DocumentSnapshot> {
            return if (collection != null && documentId != null && !documentId.isEmpty()) {
                collection.document(documentId).get().toMaybe()
            } else {
                Maybe.empty()
            }
        }

        fun <F : FirestoreModel> getByDocumentId(collection: CollectionReference, documentId: String, clazz: KClass<F>): Maybe<F> {
            return if (collection != null && documentId != null && !documentId.isEmpty()) {
                collection.document(documentId).get().toMaybe()
                        .map { documentSnapshot -> create(documentSnapshot, clazz) }

            } else {
                Maybe.empty()
            }
        }

        fun <F : FirestoreModel> getFirstByQuery(query: Query?, clazz: KClass<F>): Maybe<F> {
            return if (query != null) {
                getByQuery(query, clazz).firstElement()
            } else {
                Maybe.empty()
            }
        }

        fun <F : FirestoreModel> getByQuery(query: Query?, clazz: KClass<F>): Observable<F> {
            return if (query != null) {
                query.get().toObservable()
                        .map {
                            create(it, clazz)
                        }
            } else {
                Observable.empty()
            }
        }*/
    }

    var documentChange: DocumentChange? = null @Exclude get
    var documentSnapshot: DocumentSnapshot? = null @Exclude get
    var documentReference: DocumentReference? = null @Exclude get
    var documentId: String? = null @Exclude get
    val longId: Long @Exclude get() = documentId?.hashCode()?.toLong() ?: -1
    //var longId: Long = -1 @Exclude get
}