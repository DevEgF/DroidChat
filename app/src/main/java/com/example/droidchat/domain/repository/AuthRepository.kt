package com.example.droidchat.domain.repository

import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image

interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount): Result<Unit>
    suspend fun signIn(userName: String, password: String)
    suspend fun uploadProfilePicture(filePath: String): Result<Image>
}