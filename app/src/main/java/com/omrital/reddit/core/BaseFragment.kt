package com.omrital.reddit.core

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.omrital.reddit.dagger.AppComponent

abstract class BaseFragment: DialogFragment() {
    abstract fun inject(appComponent: AppComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getAppComponent())
    }

    private fun getAppComponent(): AppComponent {
        return (activity as MainActivity).getAppComponent()
    }
}