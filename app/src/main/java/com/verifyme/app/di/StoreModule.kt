package com.verifyme.app.di

import com.verifyme.app.data.repository.StoreRepositoryImpl
import com.verifyme.app.data.datasource.local.database.LocalDatabase
import com.verifyme.app.data.datasource.local.database.dao.ProfileDao
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @Singleton
    fun provideStoreModule(dataSource: RemoteDataSource, localDataBase: ProfileDao) : StoreRepository =
        StoreRepositoryImpl(dataSource, localDataBase)

}