package com.verifyme.app.data.datasource.remote.network

import com.verifyme.app.data.datasource.local.PreferencesManager
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class TokenInterceptor @Inject constructor(private val localDataStore: LocalDataStoreSource): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            val token = runBlocking {
                localDataStore.getData(PreferencesManager.TOKEN_KEY)
            }
            if(!token.isEmpty())
                header("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}
