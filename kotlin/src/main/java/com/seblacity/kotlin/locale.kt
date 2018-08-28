package com.seblacity.kotlin

import android.content.res.Resources
import android.os.Build

/**
 * Created by Onur on 15.7.2018.
 */

val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Resources.getSystem().configuration.locales.get(0)
} else {
    Resources.getSystem().configuration.locale
}

val language = locale.language