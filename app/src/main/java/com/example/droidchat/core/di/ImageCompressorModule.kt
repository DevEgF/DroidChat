package com.example.droidchat.core.di

import com.example.droidchat.core.image.ImageCompressor
import com.example.droidchat.core.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor
}