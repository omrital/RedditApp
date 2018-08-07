package com.omrital.reddit.dagger

import com.omrital.reddit.core.MainActivity
import com.omrital.reddit.core.MainViewModelFactory
import com.omrital.reddit.screens.favorites.FavoritesFragment
import com.omrital.reddit.screens.fullPost.FullItemFragment
import com.omrital.reddit.screens.food.FoodFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    PresentationModule::class])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(recentFragment: FoodFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(fullPostFragment: FullItemFragment)

    fun getMainViewModelFactory(): MainViewModelFactory
//    fun getChannelViewModelFactory(): RecentViewModelFactory
}
