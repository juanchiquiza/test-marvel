package com.grability.utils

import android.util.Patterns
import android.widget.EditText
import java.lang.StringBuilder
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
    try {
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(toByteArray())
        val messageDigest = digest.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun EditText.isValidEmail():
        Boolean = this.text.toString().isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()