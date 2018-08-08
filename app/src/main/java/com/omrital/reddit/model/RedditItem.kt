package com.omrital.reddit.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RedditItem(@PrimaryKey var id: String = "",
                      var title: String = "",
                      var name: String = "",
                      var imageUrl: String = "",
                      var webPageLink: String = ""): RealmObject()