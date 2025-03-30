package com.example.droidchat.ui.di

import com.example.droidchat.ui.feature.signup.state.SignUpFormState
import com.example.droidchat.ui.feature.signup.viewmodel.SignUpFormValidator
import com.example.droidchat.ui.feature.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FormValidatorModule {
    @Binds
    fun bindFormValidator(signUpFormValidator: SignUpFormValidator):FormValidator<SignUpFormState>
}