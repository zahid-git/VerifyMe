package com.verifyme.app.data.datasource.local.store

import com.verifyme.app.data.datasource.local.PreferencesManager
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import javax.inject.Inject

class LocalDataStoreSourceImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
): LocalDataStoreSource {


    override suspend fun saveData(key: String, data: String) {
        preferencesManager.saveString(key, data)
    }

    override suspend fun getData(key: String): String {
        preferencesManager.getStringOnce(key).let {
            return it.toString()
        }
        return ""
    }
}