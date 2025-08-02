package com.chinky.family.domain.usecase

import com.chinky.family.domain.utils.AESEncryption

class DecryptStringUseCase (val aesEncryption: AESEncryption){
    suspend fun decryptString(encryptedData: ByteArray, password: String): String {
        return aesEncryption.decryptAES(encryptedData, aesEncryption.createAESKey(password))
    }
}