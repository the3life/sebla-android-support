package com.seblacity.rx.google.task.completable

import com.seblacity.rx.google.task.TaskListenerFactory
import io.reactivex.CompletableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class CompletableTaskListenerFactory : TaskListenerFactory<Void, CompletableEmitter> {
    override fun createOnSuccessListener(emitter: CompletableEmitter) = CompletableEmitterSuccessListener(emitter)
    override fun createOnFailureListener(emitter: CompletableEmitter) = CompletableEmitterFailureListener(emitter)
}