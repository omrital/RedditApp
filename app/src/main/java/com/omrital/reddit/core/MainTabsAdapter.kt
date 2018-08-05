package com.omrital.reddit.core

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainTabsAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private var tabs: ArrayList<TabData> = ArrayList()

    fun addTab(tabData: TabData) {
        tabs.add(tabData)
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].title
    }

    override fun getCount(): Int {
        return tabs.size
    }
}

class TabData(val title: String,
              val fragment: Fragment)