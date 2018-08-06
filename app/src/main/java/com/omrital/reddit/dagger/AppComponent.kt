package com.omrital.reddit.dagger

import com.omrital.reddit.core.MainActivity
import com.omrital.reddit.core.MainViewModelFactory
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.screens.channel.ChannelViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun getMainViewModelFactory(): MainViewModelFactory
    fun getChannelViewModelFactory(): ChannelViewModelFactory
    fun getRedditItemsInteractor(): RedditItemsInteractor
}
