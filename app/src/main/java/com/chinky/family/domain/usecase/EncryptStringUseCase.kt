package com.chinky.family.domain.usecase

import com.chinky.family.domain.utils.AESEncryption

class EncryptStringUseCase (val aesEncryption: AESEncryption){
    suspend fun encryptString(stringValue: String, password: String) : ByteArray {
        return aesEncryption.encryptAES(stringValue, aesEncryption.createAESKey(password))
    }
}