package com.omrital.reddit.communication

import org.jdeferred2.Promise

interface ResponseParser<T> {
    fun parse(response: String): Promise<T, errorMessage, progress>
}