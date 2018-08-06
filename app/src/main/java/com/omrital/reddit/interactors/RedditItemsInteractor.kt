package com.omrital.reddit.interactors

import com.omrital.reddit.communication.errorMessage
import com.omrital.reddit.communication.progress
import com.omrital.reddit.model.RedditItem
import org.jdeferred2.Promise
import javax.inject.Inject

interface RedditItemsInteractorType {
    fun getItems(): Promise<List<RedditItem>, errorMessage, progress>
}

class RedditItemsInteractor @Inject constructor(){

}