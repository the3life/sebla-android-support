package com.seblacity.rx.google.task.maybe

import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.MaybeEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class MaybeEmitterFailureListener<T>(private val emitter: MaybeEmitter<T>) : OnFailureListener {
    override fun onFailure(exception: Exception) = emitter.onError(exception)
}