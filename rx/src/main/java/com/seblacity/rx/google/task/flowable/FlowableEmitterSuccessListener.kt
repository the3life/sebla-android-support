package com.seblacity.rx.google.task.flowable

import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.FlowableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class FlowableEmitterSuccessListener<T>(private val emitter: FlowableEmitter<T>) : OnSuccessListener<T> {
    override fun onSuccess(t: T?) {
        t?.let {
            emitter.onNext(it)
        }

        emitter.onComplete()
    }
}