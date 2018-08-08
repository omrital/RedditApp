package com.omrital.reddit.network.requests

import com.omrital.reddit.Constants.RequestParams
import com.omrital.reddit.network.RedditUrlsBuilder
import com.omrital.reddit.network.model.RedditRequestStructure

class RedditItemsRequest(limit: Int,
                         channel: String,
                         after: String? = null,
                         override var params: HashMap<String, String> = HashMap(),
                         override var URL: String = ""): RedditRequestStructure {

    init {
        URL = RedditUrlsBuilder.buildChannelUrl(channel)
        params[RequestParams.KEY_LIMIT] = limit.toString()

        if(after != null) {
            params[RequestParams.KEY_AFTER] = after
        }
    }
}