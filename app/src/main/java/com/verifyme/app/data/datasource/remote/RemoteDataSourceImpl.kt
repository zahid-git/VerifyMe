package com.verifyme.app.data.datasource.remote

import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.datasource.remote.network.ApiService
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.StoreInfoDetailsModel
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.data.model.response.UsersListResponseModel
import com.verifyme.app.domain.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun login(requestModel: LoginRequestModel): Response<BaseResponseModel<LoginResponseModel>> {
        return apiService.doLogin(requestModel)
    }

    override suspend fun getStoreInformation(): Response<BaseResponseModel<StoreInfoResponseModel>> {
        return apiService.getStoreInformation()
    }

    override suspend fun getStoreDetailsInfo(storeId: String): Response<BaseResponseModel<StoreInfoDetailsModel>> {
        return apiService.getStoreDetailsInfo(storeId)
    }

    override suspend fun getUserList(storeId: String, pageNumber: Int): Response<BaseResponseModel<UsersListResponseModel>> {
        return apiService.getUsersList(storeId = storeId, pageNumber = pageNumber)
    }

}