package com.omrital.reddit.screens.recent

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omrital.reddit.R
import com.omrital.reddit.model.RedditItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reddit_item_row.view.*

class RecentAdapter(val context: Context, var items: ArrayList<RedditItem>): RecyclerView.Adapter<RedditItemViewHolder>() {

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
        holder.set(item)
    }
}


class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun set(item: RedditItem) {
        itemView.title.text = item.title
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.with(itemView.context).load(item.imageUrl).into(itemView.image, object: Callback {
            override fun onSuccess() {

                //  add null check it is async progress bar can be null !!!


                itemView.progressBar.visibility = View.GONE
            }
            override fun onError() {

                //  add null check it is async progress bar can be null !!!


                itemView.progressBar.visibility = View.GONE
            }
        })
    }
}
