package com.omrital.reddit.widgets

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.omrital.reddit.R
import com.omrital.reddit.Utils.Keyboard
import kotlinx.android.synthetic.main.search_bar.view.*

class SearchBar: LinearLayout {

    private var before: String = ""
    var searchCallback: ((String, String) -> Unit)? = null
    var keyboard: Keyboard? = null

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

        clearButton.setOnClickListener {
            searchField.setText("")
        }
        searchField.setOnEditorActionListener { _,_,_ ->
            keyboard?.hide(searchField)
            true
        }
    }

    private fun addTextChangeListener() {
        searchField.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val after = s.toString()
                clearButton.visibility = if(after.isEmpty()) View.GONE else View.VISIBLE
                searchCallback?.invoke(before, after)
            }
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                before = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
}