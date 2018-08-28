package com.seblacity.rx.google.task.flowable

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.TaskListenerFactory
import com.seblacity.rx.google.task.TaskOnSubscribe
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe

/**
 * Created by Onur on 9.7.2018.
 */
class FlowableTaskOnSubscribe<T>(task: Task<T>, factory: TaskListenerFactory<T, FlowableEmitter<T>>) : TaskOnSubscribe<T, FlowableEmitter<T>>(task, factory), FlowableOnSubscribe<T>