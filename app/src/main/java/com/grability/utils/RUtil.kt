package com.grability.utils

import android.annotation.SuppressLint
import com.grability.GrabilityApplication

@SuppressLint("DefaultLocale")
class RUtil {

    companion object {
        val context = GrabilityApplication.getInstance()
        fun rString(resId: Int): String {
            return GrabilityApplication.getInstance().getString(resId)
        }
    }
}