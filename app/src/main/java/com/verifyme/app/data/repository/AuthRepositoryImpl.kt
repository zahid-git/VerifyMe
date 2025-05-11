package com.verifyme.app.data.repository

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.datasource.remote.network.NetworkCallback
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : AuthRepository, NetworkCallback() {

    override suspend fun checkLogin(email: String, password: String): Flow<DataResult<BaseResponseModel<LoginResponseModel>>>  = flow {
        try {
            val loginRequest = LoginRequestModel(emailAddress = email, password = password)
            val response = safeAPICall { remoteDataSource.login(loginRequest) }
            emit(response)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)


}