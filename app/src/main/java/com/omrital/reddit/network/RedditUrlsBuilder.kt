package com.omrital.reddit.network

class RedditUrlsBuilder {
    companion object {
        private const val baseURL = "http://www.reddit.com"
        private const val responseFormat = "json"

        fun buildChannelUrl(channel: String): String {
            return "$baseURL/r/$channel.$responseFormat"
        }
    }
}