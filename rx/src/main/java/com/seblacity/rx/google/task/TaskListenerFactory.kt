package com.seblacity.rx.google.task

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

/**
 * Created by Onur on 9.7.2018.
 */
interface TaskListenerFactory<T, in E> {
    fun createOnSuccessListener(emitter: E): OnSuccessListener<T>
    fun createOnFailureListener(emitter: E): OnFailureListener
}