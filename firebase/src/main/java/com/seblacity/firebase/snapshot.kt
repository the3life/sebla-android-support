package com.seblacity.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.seblacity.rx.google.task.toMaybe
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

fun DocumentSnapshot.intId(): Int = this.id.hashCode()
fun DocumentSnapshot.longId(): Long = this.intId().toLong()

fun Query.toDocumentsFlowable(mode: BackpressureStrategy) = Flowable.create<List<DocumentSnapshot>>({ emitter ->
    addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        if (firebaseFirestoreException != null) {
            return@addSnapshotListener emitter.onError(firebaseFirestoreException)
        }

        if (querySnapshot == null) {
            return@addSnapshotListener emitter.onComplete()
        }

        emitter.onNext(querySnapshot.documents)
    }
}, mode)

fun Query.toDocumentsObservable() = Observable.create<DocumentSnapshot> { emitter ->
    addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        if (firebaseFirestoreException != null) {
            return@addSnapshotListener emitter.onError(firebaseFirestoreException)
        }

        if (querySnapshot == null || querySnapshot.isEmpty) {
            return@addSnapshotListener emitter.onComplete()
        }

        querySnapshot.documents.forEach { documentSnapshot ->
            emitter.onNext(documentSnapshot)
        }

        emitter.onComplete()
    }
}

fun Query.toChangesFlowable(mode: BackpressureStrategy) = Flowable.create<List<DocumentChange>>({ emitter ->
    addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        if (firebaseFirestoreException != null) {
            return@addSnapshotListener emitter.onError(firebaseFirestoreException)
        }

        if (querySnapshot == null) {
            return@addSnapshotListener emitter.onComplete()
        }

        emitter.onNext(querySnapshot.documentChanges)
    }
}, mode)

fun Query.toChangesObservable() = Observable.create<DocumentChange> { emitter ->
    addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        if (firebaseFirestoreException != null) {
            return@addSnapshotListener emitter.onError(firebaseFirestoreException)
        }

        if (querySnapshot == null || querySnapshot.isEmpty) {
            return@addSnapshotListener emitter.onComplete()
        }

        querySnapshot.documentChanges.forEach { documentChange ->
            emitter.onNext(documentChange)
        }

        emitter.onComplete()
    }
}

fun Task<DocumentSnapshot>.toMaybe(): Maybe<DocumentSnapshot> {
    return toMaybe()
            .flatMap {
                if (!it.exists()) {
                    return@flatMap Maybe.empty<DocumentSnapshot>()
                }

                return@flatMap Maybe.just(it)
            }
}