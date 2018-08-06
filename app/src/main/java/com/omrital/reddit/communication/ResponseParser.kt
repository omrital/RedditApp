package com.omrital.reddit.communication

import com.omrital.reddit.communication.model.RedditResponseStructure
import org.jdeferred2.Promise

interface ResponseParser<T> {
    fun parse(response: RedditResponseStructure): Promise<T, ErrorMessage, Progress>
}