package com.omrital.reddit.core

import android.app.Application
import com.omrital.reddit.dagger.AppComponent
import com.omrital.reddit.dagger.AppModule
import com.omrital.reddit.dagger.DaggerAppComponent

class RedditApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = initDagger(this)
    }

    private fun initDagger(app: RedditApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
}