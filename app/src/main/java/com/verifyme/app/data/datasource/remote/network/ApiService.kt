package com.verifyme.app.data.datasource.remote.network

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.model.response.StoreInfoDetailsModel
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.data.model.response.UsersListResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun doLogin(@Body body: LoginRequestModel? = null):  Response<BaseResponseModel<LoginResponseModel>>

    @GET("getStoreInformation")
    suspend fun getStoreInformation():  Response<BaseResponseModel<StoreInfoResponseModel>>

    @GET("getStoreInformation")
    suspend fun getStoreDetailsInfo( @Query("storeId") storeId: String ): Response<BaseResponseModel<StoreInfoDetailsModel>>

    @GET("getUsersList")
    suspend fun getUsersList(
        @Query("storeId") storeId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("userId") userId: String? = null,
        @Query("id") id: String? = null,
        @Query("idType") idType: String? = null,
        @Query("country") country: String? = null,
        @Query("verified") verified: String? = null,
    ): Response<BaseResponseModel<UsersListResponseModel>>



}