package com.example.droidchat.data.repository

import com.example.droidchat.data.mapper.toCreateAccountRequest
import com.example.droidchat.data.network.datasource.NetworkDataSource
import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
): AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount) {
        networkDataSource.signUp(createAccount.toCreateAccountRequest())
    }

    override suspend fun signIn(userName: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                userName = userName,
                password = password
            )
        )
    }
}