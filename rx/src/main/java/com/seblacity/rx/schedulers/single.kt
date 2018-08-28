package com.seblacity.rx.schedulers

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 10.8.2018.
 */
fun <T> Single<T>.subscribeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Single<T>.subscribeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Single<T>.subscribeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Single<T>.subscribeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Single<T>.subscribeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Single<T>.subscribeOnUi() = apply { subscribeOn(AndroidSchedulers.mainThread()) }

fun <T> Single<T>.observeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Single<T>.observeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Single<T>.observeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Single<T>.observeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Single<T>.observeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Single<T>.observeOnMain() = apply { subscribeOn(AndroidSchedulers.mainThread()) }