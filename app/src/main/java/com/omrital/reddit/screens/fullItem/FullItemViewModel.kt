package com.omrital.reddit.screens.fullItem

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.network.RedditUrlsBuilder
import com.omrital.reddit.providers.SelectedItemProvider
import javax.inject.Inject

interface FullItemViewModelType {
    fun onStarClick()
}

class FullItemViewModel @Inject constructor(val selectedItemProvider: SelectedItemProvider,
                                            val interactor: RedditItemsInteractor): ViewModel(), FullItemViewModelType {

    val title: MutableLiveData<String> = MutableLiveData()
    val Url: MutableLiveData<String> = MutableLiveData()
    val starred: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val item = selectedItemProvider.getSelectedItem()

        if(item != null) {
            title.value = item.title
            Url.value = item.webPageLink

            RedditUrlsBuilder.buildChannelUrl(item.id)

            starred.value = interactor.getItem(item.id) != null
        }
    }

    override fun onStarClick() {
        val isStarred = starred.value
        val item = selectedItemProvider.getSelectedItem()

        if (isStarred != null) {
            if(isStarred) {
                interactor.deleteItem(item?.id)
            } else {
                interactor.saveItem(item)
            }
            starred.postValue(!isStarred)
        }
    }
}