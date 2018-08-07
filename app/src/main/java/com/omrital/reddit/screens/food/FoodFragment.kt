package com.omrital.reddit.screens.food

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
import com.omrital.reddit.screens.RedditItemsAdapter
import kotlinx.android.synthetic.main.fragment_channel.*
import javax.inject.Inject

class FoodFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var adapter: RedditItemsAdapter
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
        bindViewModel()
    }

    private fun setupViews() {
        setupRecycler()
        setupSwipeToRefresh()
    }

    private fun setupRecycler() {
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
    }

    private fun setupSwipeToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            viewModel.load()
        }
    }

    private fun bindViewModel() {
        viewModel.redditItems.observe(this, Observer {
            it?.let {
                it1 -> adapter.refreshItems(it1)
            }
        })
        viewModel.progress.observe(this, Observer {
            it?.let {
//                progressBar.visibility = if(it) View.VISIBLE else View.GONE
                swipeToRefresh.isRefreshing = it
            }
        })
        viewModel.emptyState.observe(this, Observer {
            it?.let {
                emptyState.text = it
            }
        })
    }
}