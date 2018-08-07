package com.omrital.reddit.network.model

interface RedditRequestStructure {
    var URL: String
    var params: HashMap<String, String>
}
