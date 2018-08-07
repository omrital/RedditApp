package com.omrital.reddit.screens.food

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.omrital.reddit.R
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.model.RedditBulk
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

interface RecentViewModelType {
    fun load()
}

class RecentViewModel @Inject constructor(private val redditItemsInteractor: RedditItemsInteractor,
                                          private val context: Context): ViewModel(), RecentViewModelType {

    val redditItems: MutableLiveData<List<RedditItem>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: MutableLiveData<String> = MutableLiveData()

    private val allItems = ArrayList<RedditItem>()

    init {
        load()
    }

    override fun load() {
        loadRemotePosts()
    }

    private fun loadRemotePosts() {
        progress.postValue(true)
        val getItems = redditItemsInteractor.getItems()

        getItems.done {
            onGetItemsSuccess(it)
        }
        getItems.fail {
            onGetItemsFail(it)
        }
    }

    private fun onGetItemsSuccess(bulk: RedditBulk) {
        progress.postValue(false)

        if(bulk.items.isEmpty()) {
            emptyState.postValue(context.resources.getString(R.string.empty_state_no_items))
        } else {
            emptyState.postValue("")
        }
        redditItems.postValue(bulk.items)
    }

    private fun onGetItemsFail(errorMessage: String) {
        progress.postValue(false)
        emptyState.postValue(errorMessage)
    }
}

//class RecentViewModelFactory @Inject constructor(private val channelViewModel: RecentViewModel) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(RecentViewModel::class.java)) {
//            return channelViewModel as T
//        }
//        throw IllegalArgumentException("Unknown class name")
//    }
//}