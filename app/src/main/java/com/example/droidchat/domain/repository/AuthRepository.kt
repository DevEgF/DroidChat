package com.example.droidchat.domain.repository

import com.example.droidchat.domain.model.CreateAccount

interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount): Result<Unit>
    suspend fun signIn(userName: String, password: String)
}