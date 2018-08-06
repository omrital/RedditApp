package com.omrital.reddit.dagger

import com.omrital.reddit.core.MainActivity
import com.omrital.reddit.core.MainViewModelFactory
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.screens.recent.RecentViewModelFactory
import com.omrital.reddit.screens.recent.RecentFragment
import com.omrital.reddit.screens.favorites.FavoritesFragment
import com.omrital.reddit.screens.fullPost.fullPostFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(recentFragment: RecentFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(fullPostFragment: fullPostFragment)

    fun getMainViewModelFactory(): MainViewModelFactory
    fun getChannelViewModelFactory(): RecentViewModelFactory
    fun getRedditItemsInteractor(): RedditItemsInteractor
}
