package com.seblacity.rx.google.task.single

import com.seblacity.rx.google.task.TaskListenerFactory
import io.reactivex.SingleEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class SingleTaskListenerFactory<T> : TaskListenerFactory<T, SingleEmitter<T>> {
    override fun createOnSuccessListener(emitter: SingleEmitter<T>) = SingleEmitterSuccessListener(emitter)
    override fun createOnFailureListener(emitter: SingleEmitter<T>) = SingleEmitterFailureListener(emitter)
}