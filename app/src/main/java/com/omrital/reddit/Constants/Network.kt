package com.omrital.reddit.Constants

class Network {
    companion object {
        private const val domain = "www.reddit.com"
        const val BASE_URL = "http://$domain"
        const val BASE_URL_SECURE = "https://$domain"
        const val RESPONSE_FORMAT = "json"
    }
}