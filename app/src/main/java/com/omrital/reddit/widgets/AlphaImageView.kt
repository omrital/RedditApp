package com.omrital.reddit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView

class AlphaImageview : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            alpha = 0.5f
            true
        } else if (event.action == MotionEvent.ACTION_UP) {
            alpha = 1.0f
            callOnClick()
            true
        } else {
            alpha = 1.0f
            true
        }
    }
}
