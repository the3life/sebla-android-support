package com.seblacity.kotlin

import java.util.*

/**
 * Created by Onur on 8.8.2018.
 */
fun Long.toDate(): Date = Date(this)

fun Long.toCalendar(): Calendar = Calendar.getInstance().also { it.timeInMillis = this }

fun Any.now(): Date {
    return calendar().toDate()
}

fun Any.calendar(): Calendar {
    return Calendar.getInstance()
}

fun Date.isToday(): Boolean {
    return isSameDay(this, now())
}

fun Date.isThisYear(): Boolean {
    return isSameYear(this, now())
}

fun Calendar.isToday(): Boolean {
    return isSameDay(this, calendar())
}

fun Calendar.isThisYear(): Boolean {
    return isSameYear(this, calendar())
}

fun Any.isSameDay(date1: Date, date2: Date): Boolean {
    return isSameDay(date1.toCalendar(), date2.toCalendar())
}

fun Any.isSameYear(date1: Date, date2: Date): Boolean {
    return isSameYear(date1.toCalendar(), date2.toCalendar())
}

fun Any.isSameDay(calendar1: Calendar, calendar2: Calendar): Boolean {
    return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
            calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
}

fun Any.isSameYear(calendar1: Calendar, calendar2: Calendar): Boolean {
    return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
}

fun Date.toCalendar(): Calendar = Calendar.getInstance().also { it.time = this }
fun Calendar.toDate(): Date = time