package com.omrital.reddit.model

class RedditBulk(val items: List<RedditItem>,
                 val before: String,
                 val after: String)