package com.seblacity.rx.google.task.single

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.TaskListenerFactory
import com.seblacity.rx.google.task.TaskOnSubscribe
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe

/**
 * Created by Onur on 9.7.2018.
 */
class SingleTaskOnSubscribe<T>(task: Task<T>, factory: TaskListenerFactory<T, SingleEmitter<T>>) : TaskOnSubscribe<T, SingleEmitter<T>>(task, factory), SingleOnSubscribe<T>