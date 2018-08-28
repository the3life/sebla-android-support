package com.seblacity.kotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.showApplicationDetails() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)

    intent.data = Uri.fromParts("package", packageName, null)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    startActivity(intent)
}