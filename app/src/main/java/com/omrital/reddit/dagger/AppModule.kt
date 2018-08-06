package com.omrital.reddit.dagger

import android.app.Application
import android.content.Context
import com.omrital.reddit.communication.RequestDispatcher
import com.omrital.reddit.interactors.RedditItemsInteractor
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
}