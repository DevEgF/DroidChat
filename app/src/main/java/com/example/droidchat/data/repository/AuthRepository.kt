package com.example.droidchat.data.repository

import com.example.droidchat.domain.model.CreateAccount

interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount)
    suspend fun signIn(userName: String, password: String)
}