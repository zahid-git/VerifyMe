package com.verifyme.app.data.datasource.remote.network

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun doLogin(@Body body: LoginRequestModel? = null):  Response<BaseResponseModel<LoginResponseModel>>




}