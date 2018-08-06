package com.omrital.reddit.screens.fullPost

import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.dagger.AppComponent

class fullPostFragment: BaseFragment() {

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}