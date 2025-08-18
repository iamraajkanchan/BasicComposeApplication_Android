package com.chinky.family.di

import android.content.Context
import android.net.ConnectivityManager
import com.chinky.family.domain.usecase.DecryptStringUseCase
import com.chinky.family.domain.usecase.EncryptStringUseCase
import com.chinky.family.domain.utils.AESEncryption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAESEncryption(): AESEncryption = AESEncryption()

    @Provides
    @Singleton
    fun provideEncryptUseCase(aesEncryption: AESEncryption): EncryptStringUseCase =
        EncryptStringUseCase(aesEncryption)

    @Provides
    @Singleton
    fun provideDecryptUseCase(aesEncryption: AESEncryption) : DecryptStringUseCase =
        DecryptStringUseCase(aesEncryption)

}