package com.example.droidchat.data.manager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object CryptoManager {

    fun encryptData(context: Context, key: String, data: String): String {
        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "encrypted_prefs",
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        sharedPreferences.edit().putString(key, data).apply()

        return sharedPreferences.getString(key, null) ?: ""
    }

    fun decryptData(context: Context, key: String): String {
        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "encrypted_prefs",
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        return sharedPreferences.getString(key, null) ?: ""
    }

    private fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }
}