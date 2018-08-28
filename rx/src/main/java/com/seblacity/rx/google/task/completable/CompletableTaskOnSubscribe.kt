package com.seblacity.rx.google.task.completable

import com.google.android.gms.tasks.Task
import com.seblacity.rx.google.task.TaskListenerFactory
import com.seblacity.rx.google.task.TaskOnSubscribe
import io.reactivex.CompletableEmitter
import io.reactivex.CompletableOnSubscribe

/**
 * Created by Onur on 9.7.2018.
 */
class CompletableTaskOnSubscribe(task: Task<Void>, factory: TaskListenerFactory<Void, CompletableEmitter>) : TaskOnSubscribe<Void, CompletableEmitter>(task, factory), CompletableOnSubscribe