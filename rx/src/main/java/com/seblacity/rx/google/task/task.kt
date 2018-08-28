package com.seblacity.rx.google.task

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.completable.CompletableTaskListenerFactory
import com.seblacity.rx.google.task.completable.CompletableTaskOnSubscribe
import com.seblacity.rx.google.task.maybe.MaybeTaskListenerFactory
import com.seblacity.rx.google.task.maybe.MaybeTaskOnSubscribe
import com.seblacity.rx.google.task.single.SingleTaskListenerFactory
import com.seblacity.rx.google.task.single.SingleTaskOnSubscribe
import io.reactivex.*
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.toObservable

/**
 * Created by Onur on 9.7.2018.
 */

fun <T : Any> Task<out Iterable<T>>.toObservable(): Observable<T> {
    return toSingle().toObservable().flatMap { it.toObservable() }
}

fun <T : Any> Task<out Iterable<T>>.toFlowable(): Flowable<T> {
    return toSingle().toFlowable().flatMap { it.toFlowable() }
}

fun <T> Task<T>.toSingle(): Single<T> = Single.create(SingleTaskOnSubscribe(this, SingleTaskListenerFactory()))

fun <T> Task<T>.toMaybe(): Maybe<T> = Maybe.create(MaybeTaskOnSubscribe(this, MaybeTaskListenerFactory()))

fun Task<Void>.toCompletable(): Completable = Completable.create(CompletableTaskOnSubscribe(this, CompletableTaskListenerFactory()))