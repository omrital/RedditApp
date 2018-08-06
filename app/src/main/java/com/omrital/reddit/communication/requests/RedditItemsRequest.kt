package com.omrital.reddit.communication.requests

import com.omrital.reddit.communication.model.RedditRequestStructure

class RedditItemsRequest(val limit: Int,
                         override var params: HashMap<String, String> = HashMap()) : RedditRequestStructure {

    init {
        params["limit"] = limit.toString()
    }
}