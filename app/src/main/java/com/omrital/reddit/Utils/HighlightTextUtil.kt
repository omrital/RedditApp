package com.omrital.reddit.Utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan
import android.widget.TextView

class HighlightTextUtil {
    companion object {
        fun boldWordsInsideTextView(textView: TextView?, words: Array<String>?, ignoreCase: Boolean) {
            if (textView == null || words == null || textView.text.isEmpty() || words.isEmpty()) {
                return
            }
            val text = textView.text.toString()
            textView.setText(text, TextView.BufferType.SPANNABLE)
            val s = textView.text as Spannable

            var textToSearchOn = text
            if(ignoreCase) {
                textToSearchOn = textToSearchOn.toLowerCase()
            }
            for (word in words) {
                var workToSearch = word
                if(ignoreCase) {
                    workToSearch = word.toLowerCase()
                }
                val index = textToSearchOn.indexOf(workToSearch)
                if (index != -1) {
                    s.setSpan(StyleSpan(Typeface.BOLD), index, index + word.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
    }
}