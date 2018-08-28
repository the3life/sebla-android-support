package com.seblacity.rx.schedulers

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 10.8.2018.
 */
fun Completable.subscribeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun Completable.subscribeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun Completable.subscribeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun Completable.subscribeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun Completable.subscribeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun Completable.subscribeOnUi() = apply { subscribeOn(AndroidSchedulers.mainThread()) }

fun Completable.observeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun Completable.observeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun Completable.observeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun Completable.observeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun Completable.observeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun Completable.observeOnMain() = apply { subscribeOn(AndroidSchedulers.mainThread()) }