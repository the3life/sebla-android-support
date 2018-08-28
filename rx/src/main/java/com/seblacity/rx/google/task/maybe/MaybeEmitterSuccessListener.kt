package com.seblacity.rx.google.task.maybe

import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.MaybeEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class MaybeEmitterSuccessListener<T>(private val emitter: MaybeEmitter<T>) : OnSuccessListener<T> {
    override fun onSuccess(t: T?) {
        if (t == null) {
            emitter.onComplete()
            return
        }

        emitter.onSuccess(t)
    }
}