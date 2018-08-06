package com.omrital.reddit.Utils

import android.os.Handler
import android.os.Looper

class MainThread {
    companion object {
        fun run(runnable: Runnable) {
            Handler(Looper.getMainLooper()).post(runnable)
        }

        fun isMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }
    }
}