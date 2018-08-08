package com.omrital.reddit.screens

import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.util.Patterns
import android.view.View
import com.omrital.reddit.Constants.Search
import com.omrital.reddit.R
import com.omrital.reddit.Utils.HighlightTextUtil
import com.omrital.reddit.model.RedditItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reddit_item_row.view.*

class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.progressBar.indeterminateDrawable.setColorFilter(view.context.resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
    }

    fun set(item: RedditItem, searchTerm: String, onClick: () -> Unit) {
        itemView.title.text = item.title
        itemView.progressBar.visibility = View.VISIBLE
        itemView.clickableArea.setOnClickListener {
            onClick.invoke()
        }
        if(searchTerm.length >= Search.MINIMUM) {
            HighlightTextUtil.boldWordsInsideTextView(itemView.title, arrayOf(searchTerm), true)
        }
        setImage(item)
    }

    private fun setImage(item: RedditItem) {
        if(Patterns.WEB_URL.matcher(item.imageUrl).matches()) {
            loadImageUrl(item.imageUrl)
        } else {
            itemView.progressBar.visibility = View.GONE
            itemView.image.setImageResource(R.drawable.default_placeholder)
        }
    }

    private fun loadImageUrl(url: String) {
        itemView.progressBar?.visibility = View.VISIBLE

        Picasso.with(itemView.context).load(url).into(itemView.image, object: Callback {
            override fun onSuccess() {
                if(itemView != null) {
                    itemView.progressBar?.visibility = View.GONE
                }
            }
            override fun onError() {
                if(itemView != null) {
                    itemView.progressBar?.visibility = View.GONE
                }
            }
        })
    }
}