package com.verifyme.app.data.datasource.remote

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.datasource.remote.network.ApiService
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {


    override suspend fun login(requestModel: LoginRequestModel): Response<LoginResponseModel> {
        return apiService.doLogin(requestModel)
    }


}