package com.example.droidchat.ui.feature.signin.action

sealed interface SignInActions {
    data object Success: SignInActions
    sealed interface Error : SignInActions {
        data object GenericError: Error
        data object UnauthorizedError: Error
    }
}