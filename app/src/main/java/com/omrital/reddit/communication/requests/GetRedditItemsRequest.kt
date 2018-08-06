package com.omrital.reddit.communication.requests

import com.omrital.reddit.communication.model.HttpRequest

class GetRedditItemsRequest(val skip: Int,
                            override val url: String,
                            override var body: String) : HttpRequest {

    init {
        body = "create json body"
    }
}