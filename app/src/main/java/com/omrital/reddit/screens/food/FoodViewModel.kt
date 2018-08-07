package com.omrital.reddit.screens.food

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.omrital.reddit.R
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.model.RedditBulk
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

interface FoodViewModelType {
    fun load()
}

class FoodViewModel @Inject constructor(private val interactor: RedditItemsInteractor,
                                        private val context: Context): ViewModel(), FoodViewModelType {

    val redditItems: MutableLiveData<List<RedditItem>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: MutableLiveData<String> = MutableLiveData()

    private val cache = ArrayList<RedditItem>()

    init {
        load()
    }

    override fun load() {
        loadRemotePosts()
    }

    fun onSearch(beforeChange: String, afterChange: String) {
        if (afterChange != beforeChange && afterChange.isNotEmpty()) {
            search(afterChange)
        } else if (afterChange != beforeChange) {
            refreshFromCache()
        }
    }

    private fun refreshFromCache() {
        updateCacheEmptyState()
        redditItems.postValue(cache)
    }

    private fun search(term: String) {
        
    }

    private fun loadRemotePosts() {
        cache.clear()
        redditItems.postValue(cache)
        progress.postValue(true)

        val getItems = interactor.getItems()
        getItems.done {
            onGetItemsSuccess(it)
        }
        getItems.fail {
            onGetItemsFail(it)
        }
    }

    private fun onGetItemsSuccess(bulk: RedditBulk) {
        cache.clear()
        cache.addAll(bulk.items)

        progress.postValue(false)
        updateCacheEmptyState()
        redditItems.postValue(cache)
    }

    private fun onGetItemsFail(errorMessage: String) {
        cache.clear()
        redditItems.postValue(cache)

        progress.postValue(false)
        emptyState.postValue(errorMessage)

    }

    private fun updateCacheEmptyState() {
        if(cache.isEmpty()) {
            emptyState.postValue(context.resources.getString(R.string.empty_state_no_items))
        } else {
            emptyState.postValue("")
        }
    }

    private fun updateSearchEmptyState(isEmpty: Boolean, searchTerm: String) {

    }
}