package com.seblacity.rx.google.task.completable

import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.CompletableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class CompletableEmitterSuccessListener(private val emitter: CompletableEmitter) : OnSuccessListener<Void> {
    override fun onSuccess(void: Void?) = emitter.onComplete()
}