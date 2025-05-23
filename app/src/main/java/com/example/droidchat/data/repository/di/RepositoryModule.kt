package com.example.droidchat.data.repository.di

import com.example.droidchat.domain.repository.AuthRepository
import com.example.droidchat.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}