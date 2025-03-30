package com.example.droidchat.data.network.di

import com.example.droidchat.data.network.datasource.NetworkDataSource
import com.example.droidchat.data.network.datasource.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindsNetworkDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource
}