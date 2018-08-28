package com.seblacity.rx.schedulers

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Onur on 10.8.2018.
 */
fun <T> Flowable<T>.subscribeOnNew() = apply { subscribeOn(Schedulers.newThread()) }

fun <T> Flowable<T>.subscribeOnIo() = apply { subscribeOn(Schedulers.io()) }

fun <T> Flowable<T>.subscribeOnComputation() = apply { subscribeOn(Schedulers.computation()) }

fun <T> Flowable<T>.subscribeOnSingle() = apply { subscribeOn(Schedulers.single()) }

fun <T> Flowable<T>.subscribeOnTrampoline() = apply { subscribeOn(Schedulers.trampoline()) }

fun <T> Flowable<T>.subscribeOnUi() = apply { subscribeOn(AndroidSchedulers.mainThread()) }

fun <T> Flowable<T>.observeOnNew() = apply {
    observeOn(Schedulers.newThread())
}

fun <T> Flowable<T>.observeOnIo() = apply { observeOn(Schedulers.io()) }

fun <T> Flowable<T>.observeOnComputation() = apply { observeOn(Schedulers.computation()) }

fun <T> Flowable<T>.observeOnSingle() = apply { observeOn(Schedulers.single()) }

fun <T> Flowable<T>.observeOnTrampoline() = apply { observeOn(Schedulers.trampoline()) }

fun <T> Flowable<T>.observeOnMain() = apply { observeOn(AndroidSchedulers.mainThread()) }