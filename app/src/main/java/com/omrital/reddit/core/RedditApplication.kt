package com.omrital.reddit.core

import android.app.Application
import com.omrital.reddit.dagger.AppComponent
import com.omrital.reddit.dagger.AppModule
import com.omrital.reddit.dagger.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class RedditApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = initDagger(this)
        initRealm()
    }

    private fun initDagger(app: RedditApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }
}