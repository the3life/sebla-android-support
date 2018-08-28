package com.seblacity.rx.google.task.observable

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.TaskListenerFactory
import com.seblacity.rx.google.task.TaskOnSubscribe
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Created by Onur on 9.7.2018.
 */
class ObservableTaskOnSubscribe<T>(task: Task<T>, factory: TaskListenerFactory<T, ObservableEmitter<T>>) : TaskOnSubscribe<T, ObservableEmitter<T>>(task, factory), ObservableOnSubscribe<T>