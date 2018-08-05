package com.omrital.reddit.core

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

interface MainViewModelType {
    fun onTabSelected(tab: Int)
}

class MainViewModel @Inject constructor(): ViewModel(), MainViewModelType {

    val title: MutableLiveData<String> = MutableLiveData()

    init {
        title.postValue("HELLO")
    }


    override fun onTabSelected(tab: Int) {
        when(tab) {
//            MainConstants.tabRecentPosts -> context.resources.getString(R.string.tab_name_recent_posts)
//            MainConstants.tabFavorites -> context.resources.getString(R.string.tab_name_favorites)
        }
    }
}

class MainViewModelFactory @Inject constructor(private val mainViewModel: MainViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}