package com.seblacity.rx.google.task.single

import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.SingleEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class SingleEmitterFailureListener<T>(private val emitter: SingleEmitter<T>) : OnFailureListener {
    override fun onFailure(exception: Exception) = emitter.onError(exception)
}