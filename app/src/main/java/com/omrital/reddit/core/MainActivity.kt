package com.omrital.reddit.core

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.omrital.reddit.R
import com.omrital.reddit.screens.favoritesPosts.FavoritesFragment
import com.omrital.reddit.screens.recentPosts.ChannelFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RedditApplication).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)
        initTabs()
    }

    private fun initTabs() {
        val adapter = MainTabsAdapter(supportFragmentManager)
        adapter.addTab(TabData(getString(R.string.tab_name_channel), ChannelFragment()))
        adapter.addTab(TabData(getString(R.string.tab_name_favorites), FavoritesFragment()))

        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
    }
}
