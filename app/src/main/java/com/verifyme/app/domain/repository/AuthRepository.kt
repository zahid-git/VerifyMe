package com.verifyme.app.domain.repository

import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.model.response.BaseResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {

    suspend fun checkLogin(email: String, password: String): Flow<DataResult<BaseResponseModel<LoginResponseModel>>>

}