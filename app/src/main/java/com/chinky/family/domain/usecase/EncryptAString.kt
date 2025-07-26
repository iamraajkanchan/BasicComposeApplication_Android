package com.chinky.family.domain.usecase

import com.chinky.family.domain.utils.AESEncryption

class EncryptAString(private val aesEncryption: AESEncryption) {
    suspend fun encrypt(stringValue: String) {
        aesEncryption.encryptAES(stringValue.toByteArray(), aesEncryption.createAESKey("password"))
    }
}