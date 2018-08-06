package com.omrital.reddit.Utils

import android.support.v4.view.ViewPager

class MainPagerListener(private val onPageSelected: (() -> Unit)): ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        onPageSelected.invoke()
    }
}