package com.seblacity.kotlin

import android.app.Fragment
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat

/**
 * Created by Onur on 9.8.2018.
 */
fun Fragment.getString(@StringRes stringId: Int): String? = activity?.getString(stringId)

fun Fragment.getString(@StringRes stringId: Int, vararg formatArgs: Any): String? = activity?.getString(stringId, *formatArgs)

fun Fragment.getColor(@ColorRes colorId: Int): Int? = activity?.let { ContextCompat.getColor(it, colorId) }

fun Fragment.getDrawable(@DrawableRes drawableId: Int): Drawable? = activity?.let { ContextCompat.getDrawable(it, drawableId) }

fun android.support.v4.app.Fragment.getString(@StringRes stringId: Int): String? = context?.getString(stringId)

fun android.support.v4.app.Fragment.getString(@StringRes stringId: Int, vararg formatArgs: Any): String? = context?.getString(stringId, *formatArgs)

fun android.support.v4.app.Fragment.getColor(@ColorRes colorId: Int): Int? = context?.let { ContextCompat.getColor(it, colorId) }

fun android.support.v4.app.Fragment.getDrawable(@DrawableRes drawableId: Int): Drawable? = context?.let { ContextCompat.getDrawable(it, drawableId) }