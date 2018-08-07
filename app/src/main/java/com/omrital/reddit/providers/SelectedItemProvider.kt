package com.omrital.reddit.providers

import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

interface SelectedItemProviderType {
    fun updateSelectedItem(selectedItem: RedditItem)
    fun getSelectedItem(): RedditItem?
    fun clearSelectedItem()
}

class SelectedItemProvider @Inject constructor(): SelectedItemProviderType {

    private var selectedItem: RedditItem? = null

    override fun updateSelectedItem(selectedItem: RedditItem) {
        this.selectedItem = selectedItem
    }

    override fun getSelectedItem(): RedditItem? {
        return selectedItem
    }

    override fun clearSelectedItem() {
        this.selectedItem = null
    }
}