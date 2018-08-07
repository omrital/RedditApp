package com.omrital.reddit.screens

import android.support.v7.widget.RecyclerView
import android.view.View
import com.omrital.reddit.R
import com.omrital.reddit.model.RedditItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reddit_item_row.view.*

class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun set(item: RedditItem) {
        itemView.title.text = item.title
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.with(itemView.context).load(item.imageUrl).into(itemView.image, object: Callback {
            override fun onSuccess() {
                if(itemView != null) {
                    itemView.progressBar.visibility = View.GONE
                }
            }
            override fun onError() {
                if(itemView != null) {
                    itemView.progressBar.visibility = View.GONE
                    itemView.image.setImageResource(R.drawable.default_placeholder)
                }
            }
        })
    }
}