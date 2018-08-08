package com.omrital.reddit.screens.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omrital.reddit.R
import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.core.CustomViewModelFactory
import com.omrital.reddit.dagger.AppComponent
import com.omrital.reddit.screens.listElements.RedditItemsAdapter
import kotlinx.android.synthetic.main.fragment_food.*
import javax.inject.Inject

class FavoritesFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var adapter: RedditItemsAdapter

    private lateinit var viewModel: FavoritesViewModel

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        bindViewModel()
    }

    private fun setupViews() {
        setupRecycler()
    }

    private fun setupRecycler() {
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
    }

    private fun bindViewModel() {
        viewModel.favorites.observe(this, Observer {
            if(it != null) {
                adapter.refreshItems(it)
                updateEmptyState(it.isEmpty())
            }
        })
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        emptyState.text = if(isEmpty) resources.getString(R.string.empty_state_no_favorites) else ""
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}