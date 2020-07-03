package com.example.productdiscovery.utils.common

import android.util.Log

object TDLogging {
    private val TAG = "TuanDao"
    private val LOG_ENABLE = true

    fun d(msg: String) {
        if (LOG_ENABLE) Log.d(TAG, msg)
    }

    fun i(msg: String) {
        if (LOG_ENABLE) Log.i(TAG, msg)
    }

    fun e(msg: String) {
        if (LOG_ENABLE) Log.e(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (LOG_ENABLE) Log.d(tag, msg)
    }
}
