package com.example.droidchat.data.repository

import com.example.droidchat.core.di.IoDispatcher
import com.example.droidchat.data.mapper.toCreateAccountRequest
import com.example.droidchat.data.mapper.toImageDomain
import com.example.droidchat.data.network.datasource.NetworkDataSource
import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image
import com.example.droidchat.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                networkDataSource.signUp(
                    request = createAccount.toCreateAccountRequest()
                )
            }
        }
    }

    override suspend fun signIn(userName: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                userName = userName,
                password = password
            )
        )
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> {
        return withContext(ioDispatcher) {
            runCatching {
                networkDataSource.uploadProfilePicture(
                    filePath
                ).toImageDomain()
            }
        }
    }
}