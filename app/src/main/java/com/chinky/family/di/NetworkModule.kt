package com.chinky.family.di

import android.content.Context
import android.net.ConnectivityManager
import com.chinky.family.data.db.UserDao
import com.chinky.family.data.repository.ApiService
import com.chinky.family.data.repository.UserRepository
import com.chinky.family.domain.usecase.HandleUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .callTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, userDao: UserDao, connectivityManager: ConnectivityManager): UserRepository {
        return UserRepository(apiService, userDao, connectivityManager)
    }

    @Provides
    @Singleton
    fun provideHandleUserUseCase(userRepository: UserRepository): HandleUserUseCase = HandleUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) : ConnectivityManager = context.run {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}