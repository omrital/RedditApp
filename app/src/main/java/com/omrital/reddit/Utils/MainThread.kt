package com.omrital.reddit.Utils

import android.os.Handler
import android.os.Looper

class MainThread {
    companion object {
        fun run(block: (() -> Unit)) {
            Handler(Looper.getMainLooper()).post({
                block.invoke()
            })
        }

        fun isMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }
    }
}