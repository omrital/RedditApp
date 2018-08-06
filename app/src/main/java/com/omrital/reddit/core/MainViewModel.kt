package com.omrital.reddit.core

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

interface MainViewModelType {
}

class MainViewModel @Inject constructor(): ViewModel(), MainViewModelType {





}

class MainViewModelFactory @Inject constructor(private val mainViewModel: MainViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}