package com.seblacity.kotlin

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build

/**
 * Created by Onur on 19.8.2018.
 */
fun Activity.landscape() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } else {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }
}

fun Activity.portrait() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    } else {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}