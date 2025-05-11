package com.verifyme.app.domain.datasource

interface LocalDataStoreSource {
    suspend fun saveData(key: String, data: String)
    suspend fun getData(key: String): String
}