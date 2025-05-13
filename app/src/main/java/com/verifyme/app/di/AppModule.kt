package com.verifyme.app.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.verifyme.app.BuildConfig
import com.verifyme.app.data.datasource.local.PreferencesManager
import com.verifyme.app.data.datasource.local.database.LocalDatabase
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities
import com.verifyme.app.data.datasource.local.store.LocalDataStoreSourceImpl
import com.verifyme.app.domain.datasource.RemoteDataSource
import com.verifyme.app.data.datasource.remote.RemoteDataSourceImpl
import com.verifyme.app.data.datasource.remote.network.ApiService
import com.verifyme.app.data.datasource.remote.network.TokenInterceptor
import com.verifyme.app.domain.datasource.LocalDataStoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    fun providePublicOkHttpClient(context: Context, localDataStore: LocalDataStoreSource): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(TokenInterceptor(localDataStore))
        okHttpBuilder.apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            retryOnConnectionFailure(false)
        }
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonFactory: GsonConverterFactory, okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_BASE_URL)
            addConverterFactory(gsonFactory)
            client(okHttp)
        }.build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService = retrofit.create<ApiService>()

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource = RemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun providePreferenceManager(context: Context) : PreferencesManager = PreferencesManager(context)

    @Provides
    @Singleton
    fun provideLocalDataStoreSource(preferencesManager: PreferencesManager) : LocalDataStoreSource = LocalDataStoreSourceImpl(preferencesManager)


}












