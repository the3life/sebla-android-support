package com.seblacity.rx.google.task.observable

import com.seblacity.rx.google.task.TaskListenerFactory
import io.reactivex.ObservableEmitter

/**
 * Created by Onur on 9.7.2018.
 */
class ObservableTaskListenerFactory<T> : TaskListenerFactory<T, ObservableEmitter<T>> {
    override fun createOnSuccessListener(emitter: ObservableEmitter<T>) = ObservableEmitterSuccessListener<T>(emitter)
    override fun createOnFailureListener(emitter: ObservableEmitter<T>) = ObservableEmitterFailureListener<T>(emitter)
}