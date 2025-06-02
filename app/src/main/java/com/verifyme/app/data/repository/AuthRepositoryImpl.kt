package com.verifyme.app.data.repository

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.datasource.local.PreferencesManager
import com.verifyme.app.data.datasource.local.database.LocalDatabase
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities
import com.verifyme.app.data.datasource.remote.network.NetworkCallback
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import com.verifyme.app.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataStore: LocalDataStoreSource
) : AuthRepository, NetworkCallback() {

    override suspend fun checkLogin(email: String, password: String): Flow<DataResult<BaseResponseModel<LoginResponseModel>>>  = flow {
        try {
            val loginRequest = LoginRequestModel(emailAddress = email, password = password)
            val data = safeAPICall { remoteDataSource.login(loginRequest) }
            try {
                if(data.response!!.code == 200) {
                    localDataStore.saveData(PreferencesManager.TOKEN_KEY, data.response.data!!.token)
                }
            } catch(_: Exception) { }
            emit(data)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)


}