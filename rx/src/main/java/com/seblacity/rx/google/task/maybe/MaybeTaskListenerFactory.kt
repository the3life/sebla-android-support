package com.seblacity.rx.google.task.maybe

import com.seblacity.rx.google.task.TaskListenerFactory
import io.reactivex.MaybeEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class MaybeTaskListenerFactory<T> : TaskListenerFactory<T, MaybeEmitter<T>> {
    override fun createOnSuccessListener(emitter: MaybeEmitter<T>) = MaybeEmitterSuccessListener(emitter)
    override fun createOnFailureListener(emitter: MaybeEmitter<T>) = MaybeEmitterFailureListener(emitter)
}