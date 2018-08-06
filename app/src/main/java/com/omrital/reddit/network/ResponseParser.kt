package com.omrital.reddit.network

import com.omrital.reddit.network.model.RedditResponseStructure
import org.jdeferred2.Promise

interface ResponseParser<T> {
    fun parse(response: RedditResponseStructure): Promise<T, ErrorMessage, Progress>
}