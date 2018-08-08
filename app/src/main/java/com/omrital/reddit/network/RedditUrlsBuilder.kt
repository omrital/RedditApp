package com.omrital.reddit.network

import com.omrital.reddit.Constants.Network

class RedditUrlsBuilder {
    companion object {
        fun buildChannelUrl(channel: String): String {
            return "${Network.BASE_URL}/r/$channel.${Network.RESPONSE_FORMAT}"
        }

        fun buildFullItemlUrl(channel: String, itemId: String): String {
            return "${Network.BASE_URL_SECURE}/r/$channel/comments/$itemId/"
        }
    }
}