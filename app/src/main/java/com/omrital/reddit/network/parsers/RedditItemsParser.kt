package com.omrital.reddit.network.parsers

import com.google.gson.Gson
import com.omrital.reddit.network.ErrorMessage
import com.omrital.reddit.network.Progress
import com.omrital.reddit.network.ResponseParser
import com.omrital.reddit.network.model.RedditResponseStructure
import com.omrital.reddit.model.RedditBulk
import com.omrital.reddit.model.RedditItem
import org.jdeferred2.Promise
import org.jdeferred2.impl.DeferredObject

class RedditItemsParser: ResponseParser<RedditBulk> {
    override fun parse(response: RedditResponseStructure): Promise<RedditBulk, ErrorMessage, Progress> {

        val deferred = DeferredObject<RedditBulk, ErrorMessage, Progress>()

        val gson = Gson()
        try {
            val responseModel = gson.fromJson(response.data.toString(), RedditItemsListDataStructure::class.java)

            val items = ArrayList<RedditItem>()
            for (child in responseModel.children) {
                items.add(RedditItem(child.data.id,
                        child.data.title,
                        child.data.name,
                        child.data.thumbnail,
                        child.data.url))
            }
            deferred.resolve(RedditBulk(items, responseModel.before, responseModel.after))

        } catch (e: Exception) {
            deferred.reject("failed to parse Reddit items")
        }
        return deferred.promise()
    }
}

private class RedditItemDataStructure(val id: String,
                           val name: String,
                           val title: String,
                           val thumbnail: String,
                           val url: String)

private class RedditItemStructure(val data: RedditItemDataStructure)

private class RedditItemsListDataStructure(val children: List<RedditItemStructure>,
                                           val after: String?,
                                           val before: String?)