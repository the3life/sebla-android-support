package com.seblacity.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import kotlin.reflect.KClass

/**
 * Created by Onur on 2.8.2018.
 */
val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.startActivity(cls: KClass<out Activity>) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

fun Context.startActivity(options: Bundle, cls: KClass<out Activity>) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), options)
}

fun Context.startActivity(cls: KClass<out Activity>, extras: Intent) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(extras))
}

fun Context.startActivity(options: Bundle, cls: KClass<out Activity>, extras: Intent) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(extras), options)
}

fun Context.startActivity(cls: KClass<out Activity>, flags: Int) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(flags))
}

fun Context.startActivity(options: Bundle, cls: KClass<out Activity>, flags: Int) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(flags), options)
}

fun Context.startActivity(cls: KClass<out Activity>, flags: Int, extras: Intent) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(flags).putExtras(extras))
}

fun Context.startActivity(options: Bundle, cls: KClass<out Activity>, flags: Int, extras: Intent) {
    startActivity(Intent(this, cls.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(flags).putExtras(extras), options)
}