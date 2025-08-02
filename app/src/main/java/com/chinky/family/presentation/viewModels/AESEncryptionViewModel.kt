package com.chinky.family.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinky.family.domain.usecase.DecryptStringUseCase
import com.chinky.family.domain.usecase.EncryptStringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class AESEncryptionViewModel @Inject constructor(val encryptUseCase: EncryptStringUseCase, val decryptStringUseCase: DecryptStringUseCase) : ViewModel() {
    suspend fun encryptStringUsingAES(input: String, password: String) : ByteArray {
        var result: Deferred<ByteArray>? = null
        result = viewModelScope.async {
            encryptUseCase.encryptString(input, password)
        }
        return result.await()
    }

    suspend fun decryptStringUsingAES(encryptedData: ByteArray, password: String) : String {
        return decryptStringUseCase.decryptString(encryptedData, password)
    }
}