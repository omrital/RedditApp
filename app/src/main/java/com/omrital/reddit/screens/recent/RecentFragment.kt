package com.omrital.reddit.screens.recent

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omrital.reddit.R
import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.dagger.AppComponent
import javax.inject.Inject

class RecentFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: RecentViewModelFactory
    private lateinit var viewModel: RecentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_channel, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecentViewModel::class.java)
        return view
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {




        bindViewModel()
    }

    private fun bindViewModel() {

    }
}