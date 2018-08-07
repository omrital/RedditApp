package com.omrital.reddit.network.requests

import com.omrital.reddit.network.RedditUrlsBuilder
import com.omrital.reddit.network.model.RedditRequestStructure

class RedditItemsRequest(limit: Int,
                         channel: String,
                         override var params: HashMap<String, String> = HashMap(),
                         override var URL: String = ""): RedditRequestStructure {

    init {
        URL = RedditUrlsBuilder.buildChannelUrl(channel)
        params["limit"] = limit.toString()
    }
}