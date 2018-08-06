package com.omrital.reddit.DataBase

import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

interface DataBaseType {
    fun saveRedditItems(items: List<RedditItem>)
    fun getRedditItems(): List<RedditItem>
}

class DataBase @Inject constructor(): DataBaseType {
    override fun saveRedditItems(items: List<RedditItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRedditItems(): List<RedditItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}