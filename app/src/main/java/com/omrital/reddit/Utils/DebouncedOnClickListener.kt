package com.omrital.reddit.Utils

import android.view.View
import android.os.SystemClock
import java.util.*

abstract class DebouncedOnClickListener(minimumIntervalMsillis: Long) : View.OnClickListener {

    private val minimumInterval: Long = minimumIntervalMsillis
    private val lastClickMap: MutableMap<View, Long>

    init {
        this.lastClickMap = WeakHashMap<View, Long>()
    }

    abstract fun onDebouncedClick(v: View)

    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp
        if (previousClickTimestamp == null || Math.abs(currentTimestamp - previousClickTimestamp.toLong()) > minimumInterval) {
            onDebouncedClick(clickedView)
        }
    }
}