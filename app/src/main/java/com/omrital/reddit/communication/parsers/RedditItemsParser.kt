package com.omrital.reddit.communication.parsers

import com.omrital.reddit.communication.ResponseParser
import com.omrital.reddit.communication.errorMessage
import com.omrital.reddit.communication.progress
import com.omrital.reddit.model.RedditItem
import org.jdeferred2.Promise
import org.jdeferred2.impl.DeferredObject

class RedditItemsParser: ResponseParser<List<RedditItem>> {
    override fun parse(response: String): Promise<List<RedditItem>, errorMessage, progress> {

        val deferred = DeferredObject<List<RedditItem>, errorMessage, progress>()
        val parsed = listOf<RedditItem>()



        deferred.resolve(parsed)
        return deferred.promise()
    }
}