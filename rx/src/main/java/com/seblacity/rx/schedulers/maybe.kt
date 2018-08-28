package com.seblacity.rx.schedulers

import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 10.8.2018.
 */
fun <T> Maybe<T>.subscribeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Maybe<T>.subscribeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Maybe<T>.subscribeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Maybe<T>.subscribeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Maybe<T>.subscribeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Maybe<T>.subscribeOnUi() = apply { subscribeOn(AndroidSchedulers.mainThread()) }

fun <T> Maybe<T>.observeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Maybe<T>.observeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Maybe<T>.observeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Maybe<T>.observeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Maybe<T>.observeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Maybe<T>.observeOnMain() = apply { subscribeOn(AndroidSchedulers.mainThread()) }