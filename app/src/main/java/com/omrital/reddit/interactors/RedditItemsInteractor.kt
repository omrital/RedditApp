package com.omrital.reddit.interactors

import com.omrital.reddit.Utils.MainThread
import com.omrital.reddit.core.Channels
import com.omrital.reddit.model.RedditBulk
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
}

class RedditItemsInteractor @Inject constructor(val requestDispatcher: RequestDispatcher): RedditItemsInteractorType {

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
}