package com.verifyme.app.domain.datasource

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.LoginResponseModel
import retrofit2.Response

interface RemoteDataSource {

    suspend fun login(loginRequestModel: LoginRequestModel): Response<BaseResponseModel<LoginResponseModel>>


}