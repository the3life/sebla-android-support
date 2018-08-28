package com.seblacity.rx.google.task.observable

import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.ObservableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class ObservableEmitterFailureListener<T>(private val emitter: ObservableEmitter<T>) : OnFailureListener {
    override fun onFailure(exception: Exception) = emitter.onError(exception)
}