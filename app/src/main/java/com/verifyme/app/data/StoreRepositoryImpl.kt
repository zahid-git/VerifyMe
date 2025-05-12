package com.verifyme.app.data

import android.util.Log
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.datasource.local.PreferencesManager
import com.verifyme.app.data.datasource.remote.network.NetworkCallback
import com.verifyme.app.data.model.request.LoginRequestModel
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.StoreInfoDetailsModel
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataStore: LocalDataStoreSource
) : StoreRepository, NetworkCallback() {

    override suspend fun getStoreInformation(): Flow<DataResult<BaseResponseModel<StoreInfoResponseModel>>> = flow {
        try {
            val data = safeAPICall { remoteDataSource.getStoreInformation() }
            emit(data)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getStoreDetails(storeId: String?): Flow<DataResult<BaseResponseModel<StoreInfoDetailsModel>>> = flow {
        try {
            var selectedStoreId: String = (if(storeId.isNullOrBlank())  {
                var allStores = safeAPICall { remoteDataSource.getStoreInformation() }
                allStores.response!!.data!!.storeInfo[0].id
            } else storeId).toString()
            Log.e("TAG", "getStoreDetails: "+ selectedStoreId)
            val data = safeAPICall { remoteDataSource.getStoreDetailsInfo(selectedStoreId) }
            emit(data)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)

}