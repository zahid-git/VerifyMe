package com.verifyme.app.data.repository

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.datasource.remote.RemoteDataSource
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.domain.repository.AuthRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) : AuthRepository {


    override suspend fun checkLogin(email: String, password: String): Flow<DataResult<Response<LoginResponseModel>>> {
        //API Calling
        return callbackFlow {
            val loginRequest = LoginRequestModel(emailAddress = email, password = password)
            /*trySend(
                dataSource.login(loginRequest)
            )*/
            awaitClose { cancel() }
        }
    }


}