package com.seblacity.rx.google.task.observable

import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.ObservableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class ObservableEmitterSuccessListener<T>(private val emitter: ObservableEmitter<T>) : OnSuccessListener<T> {
    override fun onSuccess(t: T?) {
        t?.let {
            emitter.onNext(it)
        }

        emitter.onComplete()
    }
}