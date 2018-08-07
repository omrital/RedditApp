package com.omrital.reddit.widgets

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.omrital.reddit.R
import kotlinx.android.synthetic.main.search_bar.view.*

class SearchBar: LinearLayout {

    var searchCallback: ((String, String) -> Unit)? = null
    var before: String = ""

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.search_bar, this, true)
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        searchField.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                searchCallback?.invoke(before, s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                before = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
}