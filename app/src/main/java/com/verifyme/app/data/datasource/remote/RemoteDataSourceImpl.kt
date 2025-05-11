package com.verifyme.app.data.datasource.remote

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.datasource.remote.network.ApiService
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.domain.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {


    override suspend fun login(requestModel: LoginRequestModel): Response<BaseResponseModel<LoginResponseModel>> {
        return apiService.doLogin(requestModel)
    }


}