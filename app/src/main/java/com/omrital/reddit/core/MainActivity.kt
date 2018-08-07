package com.omrital.reddit.core

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.omrital.reddit.R
import com.omrital.reddit.Utils.MainPagerListener
import com.omrital.reddit.dagger.AppComponent
import com.omrital.reddit.screens.favorites.FavoritesFragment
import com.omrital.reddit.screens.food.FoodFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_bar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: MainViewModelFactory
    @Inject lateinit var navigator: Navigator
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        navigator.initActivity(this)

        setContentView(R.layout.activity_main)
        initToolbar()
        initTabs()
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)
    }

    private fun initTabs() {
        val adapter = MainTabsAdapter(supportFragmentManager)
        adapter.addTab(TabData(getString(R.string.tab_name_channel), FoodFragment()))
        adapter.addTab(TabData(getString(R.string.tab_name_favorites), FavoritesFragment()))

        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
        pager.addOnPageChangeListener(MainPagerListener {
            onTabSelected()
        })
    }

    private fun onTabSelected() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(searchField.windowToken, 0)
    }

    fun getAppComponent(): AppComponent {
        return (application as RedditApplication).appComponent
    }
}
