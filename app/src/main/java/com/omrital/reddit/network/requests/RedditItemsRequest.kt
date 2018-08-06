package com.omrital.reddit.network.requests

import com.omrital.reddit.network.model.RedditRequestStructure

class RedditItemsRequest(val limit: Int,
                         override var params: HashMap<String, String> = HashMap()) : RedditRequestStructure {

    init {
        params["limit"] = limit.toString()
    }
}