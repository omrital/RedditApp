package com.omrital.reddit.DataBase

class RealmActions {
//    private var _uiRealm: Realm? = null
//
//    fun getRealm(runningFromTransaction: Boolean = false): Realm {
//        return if (MainThread.isMainThread()) {
//            if (_uiRealm == null) {
//                _uiRealm = Realm.getDefaultInstance()
//            }
//            _uiRealm!!
//        } else {
//            if (!runningFromTransaction) {
//                throw RuntimeException("Getting realm instance on back thread must happen only from within transaction")
//            }
//            Realm.getDefaultInstance()
//        }
//    }
//
//    fun transaction(block: (realm: Realm) -> Unit) {
//        var realm: Realm? = null
//        try {
//            realm = this.getRealm(true)
//            realm.executeTransaction {
//                block(it)
//            }
//        } finally {
//            if (!MainThread.isMainThread()) {
//                realm?.close()
//            }
//        }
//    }
}