package com.seblacity.rx.google.task.flowable

import com.seblacity.rx.google.task.TaskListenerFactory
import io.reactivex.FlowableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class FlowableTaskListenerFactory<T> : TaskListenerFactory<T, FlowableEmitter<T>> {
    override fun createOnSuccessListener(emitter: FlowableEmitter<T>) = FlowableEmitterSuccessListener(emitter)
    override fun createOnFailureListener(emitter: FlowableEmitter<T>) = FlowableEmitterFailureListener(emitter)
}