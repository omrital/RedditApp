package com.omrital.reddit.model

import io.realm.RealmObject

class RedditItem(val id: String,
                 val title: String,
                 val name: String,
                 val imageUrl: String,
                 val webPageLink: String): RealmObject()