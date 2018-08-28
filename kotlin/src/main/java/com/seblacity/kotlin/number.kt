package com.seblacity.kotlin

import java.util.*

/**
 * Created by Onur on 26.8.2018.
 */

fun ClosedRange<Int>.random() = Random().nextInt((endInclusive + 1) - start) + start