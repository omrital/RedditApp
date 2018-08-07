package com.omrital.reddit.interactors

import com.omrital.reddit.DataBase.DataBase
import com.omrital.reddit.Utils.MainThread
import com.omrital.reddit.core.Channels
import com.omrital.reddit.model.RealmLiveData
import com.omrital.reddit.model.RedditBulk
import com.omrital.reddit.model.RedditItem
import com.omrital.reddit.network.ErrorMessage
import com.omrital.reddit.network.Progress
import com.omrital.reddit.network.RequestDispatcher
import com.omrital.reddit.network.parsers.RedditItemsParser
import com.omrital.reddit.network.requests.RedditItemsRequest
import org.jdeferred2.Promise
import org.jdeferred2.impl.DeferredObject
import javax.inject.Inject

interface RedditItemsInteractorType {
    fun getItems(): Promise<RedditBulk, ErrorMessage, Progress>
    fun getItem(id: String): RedditItem?
    fun saveItem(item: RedditItem?)
    fun deleteItem(id: String?)
    fun getFavorites(): RealmLiveData<RedditItem>
}

class RedditItemsInteractor @Inject constructor(val requestDispatcher: RequestDispatcher,
                                                val dataBase: DataBase): RedditItemsInteractorType {

    private val limit = 25

    override fun getItems(): Promise<RedditBulk, ErrorMessage, Progress> {
        val deferred = DeferredObject<RedditBulk, ErrorMessage, Progress>()
        val promise = requestDispatcher.dispatchRequest(RedditItemsRequest(limit, Channels.FOOD), RedditItemsParser())

        promise.done {
            MainThread.run {
                deferred.resolve(it)
            }
        }.fail {
            MainThread.run {
                deferred.reject(it)
            }
        }
        return deferred.promise()
    }

    override fun getFavorites(): RealmLiveData<RedditItem> {
        return dataBase.getRedditItems()
    }

    override fun getItem(id: String): RedditItem? {
        return dataBase.getRedditItem(id)
    }

    override fun saveItem(item: RedditItem?) {
        if(item != null) {
            dataBase.saveRedditItem(item)
        }
    }

    override fun deleteItem(id: String?) {
        if(id != null) {
            dataBase.deleteRedditItem(id)
        }
    }
}