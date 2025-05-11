package com.verifyme.app.di

import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.data.repository.AuthRepositoryImpl
import com.verifyme.app.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: RemoteDataSource) : AuthRepository = AuthRepositoryImpl(dataSource)

}