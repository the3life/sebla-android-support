package com.seblacity.rx.google.task.completable

import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.CompletableEmitter
import java.lang.Exception

/**
 * Created by Onur on 9.7.2018.
 */
class CompletableEmitterFailureListener(private val emitter: CompletableEmitter) : OnFailureListener {
    override fun onFailure(exception: Exception) = emitter.onError(exception)
}