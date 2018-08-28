package com.seblacity.rx.google.task

import com.google.android.gms.tasks.Task

/**
 * Created by Onur on 9.7.2018.
 */
abstract class TaskOnSubscribe<T, in E>(private val task: Task<T>, private val factory: TaskListenerFactory<T, E>) {
    fun subscribe(emitter: E) {
        task.addOnSuccessListener(factory.createOnSuccessListener(emitter))
        task.addOnFailureListener(factory.createOnFailureListener(emitter))
    }
}