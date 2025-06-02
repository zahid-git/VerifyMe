package com.verifyme.app.data.repository

import android.util.Log
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.datasource.local.database.dao.ProfileDao
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities
import com.verifyme.app.data.datasource.remote.network.NetworkCallback
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.StoreInfoDetailsModel
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.data.model.response.UsersListResponseModel
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val profileDao: ProfileDao
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
            var selectedStoreId: String = (if (storeId.isNullOrBlank()) {
                var allStores = safeAPICall { remoteDataSource.getStoreInformation() }
                allStores.response!!.data!!.storeInfo[0].id
            } else storeId).toString()
            val data = safeAPICall { remoteDataSource.getStoreDetailsInfo(selectedStoreId) }
            profileDao.deleteAllProfiles()
            emit(data)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserList(
        storeId: String,
        pageNumber: Int
    ): Flow<DataResult<BaseResponseModel<UsersListResponseModel>>> = flow {
        try {
            val data = safeAPICall { remoteDataSource.getUserList(storeId, pageNumber) }
            try {
                var profiles: ArrayList<ProfileEntities> = arrayListOf()
                val userList = data.response?.data?.userList
                userList?.forEach { data ->
                    if(!data._id.isNullOrBlank())
                        profiles.add(ProfileEntities(
                            data._id!!,
                            (if(data.name.isNullOrBlank()) "" else data.name!!),
                            (if(data.country.isNullOrBlank()) "" else data.country!!),
                        ))
                }
                profileDao.insetProfiles(profiles)
            } catch (e: Exception) { Log.e("TAG", "getUserList: "+e.message)}
            emit(data)
        } catch (e: Exception) {
            emit(DataResult.onError(BaseResponseModel(message = e.message.toString()), -1))
        }
    }.flowOn(Dispatchers.IO)

}