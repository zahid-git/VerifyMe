package com.verifyme.app.domain.repository

import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.StoreInfoDetailsModel
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.data.model.response.UsersListResponseModel
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun getStoreInformation(): Flow<DataResult<BaseResponseModel<StoreInfoResponseModel>>>
    suspend fun getStoreDetails(storeId: String? = null): Flow<DataResult<BaseResponseModel<StoreInfoDetailsModel>>>
    suspend fun getUserList(storeId: String, pageNumber: Int): Flow<DataResult<BaseResponseModel<UsersListResponseModel>>>

}