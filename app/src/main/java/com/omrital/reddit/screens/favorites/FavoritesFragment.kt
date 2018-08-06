package com.omrital.reddit.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omrital.reddit.R
import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.dagger.AppComponent

class FavoritesFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}