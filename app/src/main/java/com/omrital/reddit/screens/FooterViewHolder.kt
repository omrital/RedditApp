package com.omrital.reddit.screens

import android.support.v7.widget.RecyclerView
import android.view.View
import com.omrital.reddit.model.FooterState
import kotlinx.android.synthetic.main.reddit_items_footer.view.*

class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun set(state: FooterState, loadMoreClick: (() -> Unit)?) {
        when(state) {
            FooterState.NONE -> {
                itemView.footer.visibility = View.GONE
                itemView.progressBar.visibility = View.GONE
                itemView.errorMessage.visibility = View.GONE
                itemView.footer.setOnClickListener(null)
            }
            FooterState.LOADING -> {
                itemView.footer.visibility = View.VISIBLE
                itemView.progressBar.visibility = View.VISIBLE
                itemView.errorMessage.visibility = View.GONE
                itemView.footer.setOnClickListener(null)
            }
            FooterState.FAILED -> {
                itemView.footer.visibility = View.VISIBLE
                itemView.progressBar.visibility = View.GONE
                itemView.errorMessage.visibility = View.VISIBLE
                itemView.footer.setOnClickListener {
                    loadMoreClick?.invoke()
                }
            }
        }
    }
}