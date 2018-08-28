package com.seblacity.rx.schedulers

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 10.8.2018.
 */
fun <T> Observable<T>.subscribeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Observable<T>.subscribeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Observable<T>.subscribeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Observable<T>.subscribeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Observable<T>.subscribeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Observable<T>.subscribeOnUi() = apply { subscribeOn(AndroidSchedulers.mainThread()) }

fun <T> Observable<T>.observeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Observable<T>.observeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Observable<T>.observeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Observable<T>.observeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Observable<T>.observeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Observable<T>.observeOnMain() = apply { subscribeOn(AndroidSchedulers.mainThread()) }