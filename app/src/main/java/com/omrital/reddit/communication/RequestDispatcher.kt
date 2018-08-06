package com.omrital.reddit.communication

import com.omrital.reddit.communication.model.RedditRequestStructure
import com.omrital.reddit.communication.model.RedditResponseStructure
import org.jdeferred2.Promise
import org.jdeferred2.impl.DeferredObject
import javax.inject.Inject

interface RequestDispatcherType {
    fun<T> dispatchRequest(request: RedditRequestStructure, parser: ResponseParser<T>): Promise<T, ErrorMessage, Progress>
}

class RequestDispatcher @Inject constructor(val httpClient: HttpClient): RequestDispatcherType {

    override fun <T> dispatchRequest(request: RedditRequestStructure, parser: ResponseParser<T>): Promise<T, ErrorMessage, Progress> {
        val deferredObject = DeferredObject<T, ErrorMessage, Progress>()
        val promise = httpClient.doGetRequest(request)

        promise.done {
            parseResponse(it, parser, deferredObject)
        }.fail {
            deferredObject.reject(it)
        }
        return deferredObject.promise()
    }

    private fun <T> parseResponse(response: RedditResponseStructure, parser: ResponseParser<T>, deferredObject: DeferredObject<T, ErrorMessage, Progress>) {
        val promise = parser.parse(response)
        promise.done {
            deferredObject.resolve(it)
        }.fail {
            deferredObject.reject(it)
        }
    }
}