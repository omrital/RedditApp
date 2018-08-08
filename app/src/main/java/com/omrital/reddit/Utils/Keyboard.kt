package com.omrital.reddit.Utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class Keyboard {

    private val inputMethodManager: InputMethodManager

    @Inject
    constructor(context: Context) {
        inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun hide(v: View?): Boolean {
        if (v != null) {
            v.clearFocus()
            return inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
        return false
    }

    fun show(v: View): Boolean {
        v.requestFocus()
        return inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
    }
}