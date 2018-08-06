package com.omrital.reddit.screens.channel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

interface ChannelViewModelType {
    fun load()
}

class ChannelViewModel @Inject constructor(private val redditItemsInteractor: RedditItemsInteractor): ViewModel(), ChannelViewModelType {

    val redditItems: MutableLiveData<RedditItem> = MutableLiveData()
    private val allItems = ArrayList<RedditItem>()


    override fun load() {

    }
}

class ChannelViewModelFactory @Inject constructor(private val channelViewModel: ChannelViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChannelViewModel::class.java)) {
            return channelViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}