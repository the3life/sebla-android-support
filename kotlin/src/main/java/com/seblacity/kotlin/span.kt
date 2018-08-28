package com.seblacity.kotlin

import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView

fun <T> Iterable<T>.joinToSpannedString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): SpannedString {
    return SpannedString(joinTo(SpannableStringBuilder(), separator, prefix, postfix, limit, truncated, transform))
}

fun CharSequence.append(value: String) = TextUtils.concat(this, value)

fun CharSequence.color(color: Int): Spannable {
    return color(0, length, color)
}

fun CharSequence.color(start: Int, end: Int, color: Int): Spannable {
    val spannable = SpannableString.valueOf(this)

    spannable.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannable
}

fun CharSequence.hyperlink(clickableText: String, span: ClickableSpan) = let {
    val spannable = this as? Spannable ?: SpannableString.valueOf(this)

    val start = indexOf(clickableText)
    val end = start + clickableText.length

    if (start == -1) {
        return spannable
    }

    spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    return@let spannable
}

fun TextView.createHyperlink(clickableText: String, span: ClickableSpan) {
    val value = text
    val string = text.toString()

    val start = string.indexOf(clickableText)
    val end = start + clickableText.length

    if (start == -1) {
        return
    }

    if (value is Spannable) {
        value.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    } else {
        val spannableString = SpannableString.valueOf(text)
        spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannableString
    }

    if (movementMethod == null || movementMethod !is LinkMovementMethod) {
        movementMethod = LinkMovementMethod.getInstance()
    }
}