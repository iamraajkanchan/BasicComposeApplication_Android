package com.chinky.family.di

import com.chinky.family.domain.usecase.DecryptStringUseCase
import com.chinky.family.domain.usecase.EncryptStringUseCase
import com.chinky.family.domain.utils.AESEncryption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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