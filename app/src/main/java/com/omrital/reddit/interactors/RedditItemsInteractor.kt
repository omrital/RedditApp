package com.omrital.reddit.interactors

import com.omrital.reddit.Utils.MainThread
import com.omrital.reddit.communication.ErrorMessage
import com.omrital.reddit.communication.Progress
import com.omrital.reddit.communication.RequestDispatcher
import com.omrital.reddit.communication.parsers.RedditItemsParser
import com.omrital.reddit.communication.requests.RedditItemsRequest
import com.omrital.reddit.model.RedditBulk
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
        val promise = requestDispatcher.dispatchRequest(RedditItemsRequest(limit), RedditItemsParser())

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