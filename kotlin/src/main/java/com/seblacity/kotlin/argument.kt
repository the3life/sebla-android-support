@file:Suppress("UNCHECKED_CAST")

package com.seblacity.kotlin

import android.app.Fragment
import android.os.Bundle
import java.io.Serializable

/**
 * Created by Onur on 2.8.2018.
 */
fun Any.argumentsOfLazy() = lazy { argumentsOf() }

fun Any.argumentsOf() = Bundle()

fun Any.argumentsOfLazy(vararg arguments: Pair<String, Serializable?>) = lazy { argumentsOf() }

fun Any.argumentsOf(vararg arguments: Pair<String, Serializable?>) = Bundle().also { bundle ->
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
}

fun Fragment.hasArgument(key: String) = arguments?.containsKey(key)

fun android.support.v4.app.Fragment.hasArgument(key: String) = arguments?.containsKey(key) ?: false

fun Fragment.withArgumentsLazy(vararg arguments: Pair<String, Serializable>) = lazy { withArguments(*arguments) }

fun Fragment.withArguments(vararg arguments: Pair<String, Serializable>) = apply {
    this.arguments = argumentsOf(*arguments)
}

fun android.support.v4.app.Fragment.withArgumentsLazy(vararg arguments: Pair<String, Serializable>) = lazy { withArguments(*arguments) }

fun android.support.v4.app.Fragment.withArguments(vararg arguments: Pair<String, Serializable>) = apply {
    this.arguments = argumentsOf(*arguments)
}

fun <T : Any> Fragment.argumentLazy(key: String) = lazy { argument<T>(key) }

fun <T : Any> Fragment.argument(key: String) = arguments?.get(key) as T?

fun <T : Any> android.support.v4.app.Fragment.argumentLazy(key: String) = lazy { argument<T>(key) }

fun <T : Any> android.support.v4.app.Fragment.argument(key: String) = arguments?.get(key) as T?

fun <T : Any> Fragment.argumentLazy(key: String, defaultValue: T? = null) = lazy { argument(key, defaultValue) }

fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) = arguments?.get(key) as? T
        ?: defaultValue

fun <T : Any> android.support.v4.app.Fragment.argumentLazy(key: String, defaultValue: T? = null) = lazy { argument(key, defaultValue) }

fun <T : Any> android.support.v4.app.Fragment.argument(key: String, defaultValue: T? = null) = arguments?.get(key) as? T
        ?: defaultValue

fun <T : Any> Fragment.argumentOrExtraLazy(key: String) = lazy { argumentOrExtra<T>(key) }

fun <T : Any> Fragment.argumentOrExtra(key: String) = arguments?.get(key) as? T
        ?: activity.extra<T>(key)

fun <T : Any> android.support.v4.app.Fragment.argumentOrExtraLazy(key: String) = lazy { argumentOrExtra<T>(key) }

fun <T : Any> android.support.v4.app.Fragment.argumentOrExtra(key: String) = arguments?.get(key) as? T
        ?: activity?.extra<T>(key)

fun <T : Any> Fragment.argumentOrExtraLazy(key: String, defaultValue: T? = null) = lazy { argumentOrExtra(key, defaultValue) }

fun <T : Any> Fragment.argumentOrExtra(key: String, defaultValue: T? = null) = arguments?.get(key) as? T
        ?: activity?.extra(key, defaultValue)

fun <T : Any> android.support.v4.app.Fragment.argumentOrExtraLazy(key: String, defaultValue: T? = null) = lazy { argumentOrExtra(key, defaultValue) }

fun <T : Any> android.support.v4.app.Fragment.argumentOrExtra(key: String, defaultValue: T? = null) = arguments?.get(key) as? T
        ?: activity?.extra(key, defaultValue)