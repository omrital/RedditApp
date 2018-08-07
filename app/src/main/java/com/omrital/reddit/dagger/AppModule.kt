package com.omrital.reddit.dagger

import android.app.Application
import android.content.Context
import com.omrital.reddit.core.Navigator
import com.omrital.reddit.interactors.RedditItemsInteractor
import com.omrital.reddit.network.RequestDispatcher
import com.omrital.reddit.providers.SelectedItemProvider
import com.omrital.reddit.screens.RedditItemsAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideRedditItemsInteractor(requestDispatcher: RequestDispatcher) = RedditItemsInteractor(requestDispatcher)

    @Provides
    @Singleton
    fun getNavigator(selectedItemProvider: SelectedItemProvider) = Navigator(selectedItemProvider)

    @Provides
    @Singleton
    fun getSelectedItemProvider() = SelectedItemProvider()

    @Provides
    fun getRedditItemsAdapter(context: Context): RedditItemsAdapter = RedditItemsAdapter(context)
}