package com.omrital.reddit.screens

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.omrital.reddit.R
import com.omrital.reddit.core.Navigator
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

class RedditItemsAdapter @Inject constructor(val context: Context, val navigator: Navigator): RecyclerView.Adapter<RedditItemViewHolder>() {

    private var items: ArrayList<RedditItem> = ArrayList()

    fun refreshItems(items: List<RedditItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItemsToBottom(items: List<RedditItem>) {
        this.items.addAll(items)
        val start = itemCount - items.size
        notifyItemRangeInserted(start, items.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {
        return RedditItemViewHolder(LayoutInflater.from(context).inflate(R.layout.reddit_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        val item = this.items[position]
        holder.set(item) {
            navigator.openFullItem(item)
        }
    }
}