package com.chinky.family.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESEncryption {

    private val encryptionScope = CoroutineScope(Dispatchers.Default)

    suspend fun encryptAES(inputString: String, secretKey: SecretKey) : ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = IvParameterSpec(ByteArray(16)) // Use a secure random IV in production
        val encryptJob = encryptionScope.async {
            cipher.apply {
                init(Cipher.ENCRYPT_MODE, secretKey, iv)
            }
            cipher.doFinal(inputString.toByteArray())
        }
        return encryptJob.await()
    }

    suspend fun decryptAES(encryptedData: ByteArray, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = IvParameterSpec(ByteArray(16)) // Use the same IV used for encryption
        val decryptJob = encryptionScope.async {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
            String(cipher.doFinal(encryptedData), Charsets.UTF_8)
        }
        return decryptJob.await()
    }

    fun encryptAES128(plainText: String, secretKey: String): String {
        val keySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = ByteArray(16)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun decryptAES128(encryptedText: String, secretKey: String): String {
        try {
            // Create cipher instance
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

            // Convert the secret key string to SecretKeySpec
            // Ensure the key is exactly 16 bytes for AES-128
            val keyBytes = secretKey.toByteArray(Charsets.UTF_8).take(16).toByteArray()
            val paddedKey = keyBytes + ByteArray(16 - keyBytes.size) // Pad with zeros if needed
            val secretKeySpec = SecretKeySpec(paddedKey, "AES")

            // Initialize IV (should match the IV used during encryption)
            // Using all zeros for this example - in real applications, use the same IV from encryption
            val iv = IvParameterSpec(ByteArray(16))

            // Initialize cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv)

            // Decode the Base64 encrypted text
            val encryptedBytes = Base64.getDecoder().decode(encryptedText)

            // Decrypt the data
            val decryptedBytes = cipher.doFinal(encryptedBytes)

            // Convert back to string
            return String(decryptedBytes, Charsets.UTF_8)

        } catch (e: Exception) {
            throw RuntimeException("Decryption failed: ${e.message}", e)
        }
    }


    fun decryptAES128WithEmbeddedIV(encryptedText: String, secretKey: String): String {
        try {
            // Decode the Base64 encrypted text
            val encryptedData = Base64.getDecoder().decode(encryptedText)

            // Extract IV (first 16 bytes) and encrypted content (remaining bytes)
            val iv = encryptedData.sliceArray(0..15)
            val encryptedBytes = encryptedData.sliceArray(16 until encryptedData.size)

            // Create cipher instance
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

            // Convert the secret key string to SecretKeySpec
            val keyBytes = secretKey.toByteArray(Charsets.UTF_8).take(16).toByteArray()
            val paddedKey = keyBytes + ByteArray(16 - keyBytes.size)
            val secretKeySpec = SecretKeySpec(paddedKey, "AES")

            // Initialize cipher with extracted IV
            val ivSpec = IvParameterSpec(iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec)

            // Decrypt the data
            val decryptedBytes = cipher.doFinal(encryptedBytes)

            // Convert back to string
            return String(decryptedBytes, Charsets.UTF_8)

        } catch (e: Exception) {
            throw RuntimeException("Decryption failed: ${e.message}", e)
        }
    }

    fun createAESKey(password: String): SecretKeySpec {
        val keyBytes = password.toByteArray(Charsets.UTF_8)
        val key = when {
            keyBytes.size >= 16 -> keyBytes.sliceArray(0..15) // Use first 16 bytes
            else -> keyBytes + ByteArray(16 - keyBytes.size)  // Pad with zeros
        }
        return SecretKeySpec(key, "AES")
    }

}