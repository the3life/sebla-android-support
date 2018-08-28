package com.seblacity.rx.google.task.maybe

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.TaskListenerFactory
import com.seblacity.rx.google.task.TaskOnSubscribe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeOnSubscribe

/**
 * Created by Onur on 9.7.2018.
 */
class MaybeTaskOnSubscribe<T>(task: Task<T>, factory: TaskListenerFactory<T, MaybeEmitter<T>>) : TaskOnSubscribe<T, MaybeEmitter<T>>(task, factory), MaybeOnSubscribe<T>