package com.omrital.reddit.dagger

import com.omrital.reddit.core.MainActivity
import com.omrital.reddit.core.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun getMainViewModelFactory(): MainViewModelFactory
}
