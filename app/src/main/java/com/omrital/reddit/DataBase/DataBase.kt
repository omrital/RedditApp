package com.omrital.reddit.DataBase

import com.omrital.reddit.model.RealmLiveData
import com.omrital.reddit.model.RedditItem
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.where
import javax.inject.Inject

interface DataBaseType {
    fun saveRedditItem(item: RedditItem)
    fun deleteRedditItem(id: String)
    fun getRedditItem(id: String): RedditItem?
    fun getRedditItems(): RealmLiveData<RedditItem>
}

class DataBase @Inject constructor(): DataBaseType {

    private val realmActions = RealmActions()

    override fun saveRedditItem(item: RedditItem) {
        realmActions.transaction {
            it.copyToRealmOrUpdate(item)
        }
    }

    override fun deleteRedditItem(id: String) {
        realmActions.transaction {
            it.where<RedditItem>().equalTo("id", id).findFirst()?.deleteFromRealm()
        }
    }

    override fun getRedditItem(id: String): RedditItem? {
        return realmActions.getRealm().where<RedditItem>().equalTo("id", id).findFirst()
    }

    override fun getRedditItems(): RealmLiveData<RedditItem> {
        return realmActions.getRealm().where<RedditItem>().findAll().sort("id").asLiveData()
    }
}

fun <T : RealmModel> RealmResults<T>.asLiveData() = RealmLiveData<T>(this)