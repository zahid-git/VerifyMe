package com.verifyme.app.di

import com.verifyme.app.data.StoreRepositoryImpl
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
    fun provideStoreModule(dataSource: RemoteDataSource, localDataStore: LocalDataStoreSource) : StoreRepository =
        StoreRepositoryImpl(dataSource, localDataStore)

}