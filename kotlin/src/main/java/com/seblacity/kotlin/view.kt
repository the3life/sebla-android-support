package com.seblacity.kotlin

import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * Created by Onur on 8.8.2018.
 */
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

var View.gone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.show() = run { visibility = View.VISIBLE }

fun View.hide() = run { visibility = View.INVISIBLE }

fun View.gone() = run { visibility = View.GONE }

fun View.getString(@StringRes stringId: Int): String = context.getString(stringId)

fun View.getString(@StringRes stringId: Int, vararg formatArgs: Any): String = context.getString(stringId, *formatArgs)

fun View.getColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(context, colorId)

fun View.getDrawable(@DrawableRes drawableId: Int): Drawable? = ContextCompat.getDrawable(context, drawableId)