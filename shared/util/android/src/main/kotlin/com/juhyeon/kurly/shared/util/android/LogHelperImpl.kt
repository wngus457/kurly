package com.juhyeon.kurly.shared.util.android

import android.util.Log
import javax.inject.Inject

class LogHelperImpl @Inject constructor() : LogHelper {
    override fun log(content: String) {
        logWhenBuildMode(Log.ERROR, content)
    }

    override fun log(type: Int, content: String) {
        logWhenBuildMode(type, content)
    }

    private fun logWhenBuildMode(type: Int, content: String) {
        val tag = "Kurly"

        if (BuildConfig.DEBUG) {
            when (type) {
                Log.DEBUG -> Log.d(tag, content)
                Log.INFO -> Log.i(tag, content)
                Log.WARN -> Log.w(tag, content)
                Log.ERROR -> Log.e(tag, content)
            }
        }
    }
}