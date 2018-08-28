@file:Suppress("UNCHECKED_CAST")

package com.seblacity.kotlin

import android.app.Activity
import android.os.Bundle
import java.io.Serializable

/**
 * Created by Onur on 2.8.2018.
 */
fun Any.extrasOfLazy() = lazy { extrasOf() }

fun Any.extrasOf(): Bundle {
    return Bundle()
}

fun Any.extrasOfLazy(vararg extras: Pair<String, Serializable?>) = lazy { extrasOf(*extras) }

fun Any.extrasOf(vararg extras: Pair<String, Serializable?>) = Bundle().also { bundle ->
    extras.forEach { bundle.putSerializable(it.first, it.second) }
}

fun Activity.hasExtra(key: String) = intent?.hasExtra(key) ?: false

fun <T : Any> Activity.extraLazy(key: String) = lazy { extra<T>(key) }

fun <T : Any> Activity.extra(key: String) = intent?.extras?.let { it[key] as T? }

fun <T : Any> Activity.extraLazy(key: String, defaultValue: T? = null) = lazy { extra(key, defaultValue) }

fun <T : Any> Activity.extra(key: String, defaultValue: T? = null) = intent?.extras?.let { it[key] as? T }
        ?: defaultValue