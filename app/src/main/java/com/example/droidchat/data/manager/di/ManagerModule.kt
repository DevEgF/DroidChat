package com.example.droidchat.data.manager.di

import com.example.droidchat.data.manager.SecureTokenManagerImpl
import com.example.droidchat.data.manager.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    @Singleton
    fun bindTokenManager(tokenManagerImpl: SecureTokenManagerImpl): TokenManager
}