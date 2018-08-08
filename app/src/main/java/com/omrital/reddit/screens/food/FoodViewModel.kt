package com.omrital.reddit.screens.food

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.omrital.reddit.Constants.Search
import com.omrital.reddit.R
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.model.FooterState
import com.omrital.reddit.model.RedditBulk
import com.omrital.reddit.model.RedditItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

interface FoodViewModelType {
    fun load()
    fun onSearch(beforeChange: String, afterChange: String)
    fun loadMoreItems()
}

class FoodViewModel @Inject constructor(private val interactor: RedditItemsInteractor,
                                        private val context: Context): ViewModel(), FoodViewModelType {

    val items: MutableLiveData<List<RedditItem>> = MutableLiveData()
    val nextItems: MutableLiveData<List<RedditItem>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val swipeToRefresh: MutableLiveData<Boolean> = MutableLiveData()
    val loadMoreState: MutableLiveData<FooterState> = MutableLiveData()
    val emptyState: MutableLiveData<String> = MutableLiveData()

    private val cache = ArrayList<RedditItem>()
    private var inProgress: Boolean = false
    private var noMoreItemsToFetch: Boolean = false
    private var searching: Boolean = false
    private var currentUserSearch: String = ""

    init {
        load()
    }

    override fun load() {
        if(inProgress) {
            return
        }
        if(searching) {
            return
        }
        loadRemotePosts()
    }

    override fun onSearch(beforeChange: String, afterChange: String) {
        if (afterChange != beforeChange && afterChange.length >= Search.MINIMUM) {
            search(afterChange)
        } else if (afterChange != beforeChange) {
            refreshFromCache()
        }
    }

    override fun loadMoreItems() {
        if(inProgress) {
            return
        }
        if(searching) {
            return
        }
        if(noMoreItemsToFetch) {
            return
        }
        loadMorePosts()
    }

    private fun refreshFromCache() {
        swipeToRefresh.postValue(true)
        searching = false
        updateCacheEmptyState()
        items.postValue(cache)
    }

    private fun search(term: String) {
        swipeToRefresh.postValue(false)
        searching = true
        currentUserSearch = term

        doAsync {
            val results = getSearchResults(term)

            uiThread {
                if(currentUserSearch == term) {
                    items.postValue(results)
                    updateSearchEmptyState(results.isEmpty(), term)
                }
            }
        }
    }

    private fun getSearchResults(term: String): List<RedditItem> {
        val term = term.toLowerCase()
        var results = ArrayList<RedditItem>()

        for(item in cache) {
            if(item.title.toLowerCase().contains(term)) {
                results.add(item)
            }
        }
        return results
    }

    private fun updateCacheEmptyState() {
        if(cache.isEmpty()) {
            emptyState.postValue(context.resources.getString(R.string.empty_state_no_items))
        } else {
            emptyState.postValue("")
        }
    }

    private fun updateSearchEmptyState(isEmpty: Boolean, searchTerm: String) {
        if(isEmpty) {
            emptyState.postValue(context.resources.getString(R.string.empty_state_search).replace("{searchTerm}", searchTerm))
        } else {
            emptyState.postValue("")
        }
    }

    private fun loadRemotePosts() {
        inProgress = true
        noMoreItemsToFetch = false

        cache.clear()
        items.postValue(cache)
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
        items.postValue(cache)
        inProgress = false
    }

    private fun onGetItemsFail(errorMessage: String) {
        cache.clear()
        items.postValue(cache)

        progress.postValue(false)
        emptyState.postValue(errorMessage)
        inProgress = false
    }

    private fun loadMorePosts() {
        inProgress = true

        loadMoreState.postValue(FooterState.LOADING)

        val lastItemName = cache[cache.size-1].name
        val getItems = interactor.getItemsAfterName(lastItemName)
        getItems.done {
            onLoadMoreSuccess(it)
        }
        getItems.fail {
            onLoadMoreFail()
        }
    }

    private fun onLoadMoreSuccess(bulk: RedditBulk) {
        if(bulk.items.isNotEmpty()) {
            cache.addAll(bulk.items)
            nextItems.postValue(bulk.items)
        } else {
            noMoreItemsToFetch = true
        }
        loadMoreState.postValue(FooterState.NONE)
        inProgress = false
    }

    private fun onLoadMoreFail() {
        loadMoreState.postValue(FooterState.FAILED)
        inProgress = false
    }
}