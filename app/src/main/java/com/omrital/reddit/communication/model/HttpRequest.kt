package com.omrital.reddit.communication.model

interface HttpRequest {
    val url: String
    val body: String
}