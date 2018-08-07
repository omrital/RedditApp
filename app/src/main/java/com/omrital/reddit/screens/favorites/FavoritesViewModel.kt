package com.omrital.reddit.screens.favorites

import android.arch.lifecycle.ViewModel
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.model.RealmLiveData
import com.omrital.reddit.model.RedditItem
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(interactor: RedditItemsInteractor): ViewModel() {

    val favorites: RealmLiveData<RedditItem> = interactor.getFavorites()
}