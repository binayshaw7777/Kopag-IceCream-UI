package com.geekymusketeers.kopag_icecream_ui.utils

import android.util.Log

object Logger {

    fun debugLog(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun Any.debugLog(tag: String? = "DEBUG_TAG") {
        Log.d(tag, this.toString())
    }

    fun debugLog(message: String) {
        Log.d("DebugLog", message)
    }

}