package com.omrital.reddit.screens

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.omrital.reddit.Constants.ItemViewTypes.Companion.FOOTER_VIEW
import com.omrital.reddit.R
import com.omrital.reddit.core.Navigator
import com.omrital.reddit.model.FooterState
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

class RedditItemsAdapter @Inject constructor(val context: Context, val navigator: Navigator): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<RedditItem> = ArrayList()
    private var footerState: FooterState = FooterState.INVISIBLE
    var onFooterClick: (() -> Unit)? = null
    var onScrollReachedEnd: (() -> Unit)? = null
    var searchTerm: String = ""

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

    fun updateFooterState(footerState: FooterState) {
        this.footerState = footerState
        notifyItemChanged(itemCount-1)
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == FOOTER_VIEW) {
            return FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.reddit_items_footer, parent, false))
        }
        return RedditItemViewHolder(LayoutInflater.from(context).inflate(R.layout.reddit_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is RedditItemViewHolder) {
            val item = this.items[position]
            holder.set(item, searchTerm) {
                navigator.openFullItem(item)
            }
        } else if(holder is FooterViewHolder) {
            holder.set(footerState, onFooterClick)

            if(footerState == FooterState.INVISIBLE) {
                onScrollReachedEnd?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == items.size) {
            return FOOTER_VIEW
        }
        return super.getItemViewType(position)
    }
}