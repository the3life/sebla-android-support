package com.seblacity.rx.google.task.flowable

import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.FlowableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class FlowableEmitterFailureListener<T>(private val emitter: FlowableEmitter<T>) : OnFailureListener {
    override fun onFailure(exception: Exception) = emitter.onError(exception)
}