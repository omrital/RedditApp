package com.omrital.reddit.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.omrital.reddit.R
import com.omrital.reddit.Utils.Keyboard
import com.omrital.reddit.Utils.MainPagerListener
import com.omrital.reddit.dagger.AppComponent
import com.omrital.reddit.screens.favorites.FavoritesFragment
import com.omrital.reddit.screens.food.FoodFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_bar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var keyboard: Keyboard
    private var selectedTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
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
            onTabSelected(it)
        })
    }

    private fun onTabSelected(selectedTab: Int) {
        this.selectedTab = selectedTab
        keyboard.hide(searchField)
    }

    fun getAppComponent(): AppComponent {
        return (application as RedditApplication).appComponent
    }

    override fun onBackPressed() {
        if(selectedTab != 0) {
            pager.setCurrentItem(0, true)
            return
        }
        super.onBackPressed()
    }
}
