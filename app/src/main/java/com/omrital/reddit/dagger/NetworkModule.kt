package com.omrital.reddit.dagger

import com.omrital.reddit.network.HttpClient
import com.omrital.reddit.network.RequestDispatcher
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpClient(okHttpClient: OkHttpClient) = HttpClient(okHttpClient)

    @Provides
    @Singleton
    fun provideRequestDispatcher(httpClient: HttpClient) = RequestDispatcher(httpClient)
}