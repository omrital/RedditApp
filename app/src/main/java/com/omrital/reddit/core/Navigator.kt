package com.omrital.reddit.core

import android.support.v7.app.AppCompatActivity
import com.omrital.reddit.model.RedditItem
import com.omrital.reddit.providers.SelectedItemProvider
import com.omrital.reddit.screens.fullItem.FullItemFragment
import javax.inject.Inject

interface NavigatorType {
    fun initActivity(activity: AppCompatActivity)
    fun openFullItem(item: RedditItem)
}

class Navigator @Inject constructor(val selectedItemProvider: SelectedItemProvider): NavigatorType {

    private lateinit var activity: AppCompatActivity
    private val dialogTag = "full_item_dialog"

    override fun initActivity(activity: AppCompatActivity) {
        this.activity = activity
    }

    override fun openFullItem(item: RedditItem) {
        if(activity.supportFragmentManager.findFragmentByTag(dialogTag) != null) {
            return
        }
        selectedItemProvider.updateSelectedItem(item.getCopy())

        val fullItem = FullItemFragment()
        fullItem.show(activity.supportFragmentManager, dialogTag)
    }
}