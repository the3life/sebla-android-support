package com.seblacity.rx.google.task.single

import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.SingleEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class SingleEmitterSuccessListener<T>(private val emitter: SingleEmitter<T>) : OnSuccessListener<T> {
    override fun onSuccess(t: T?) {
        if (t == null) {
            emitter.onError(NullPointerException())
            return
        }

        emitter.onSuccess(t)
    }
}