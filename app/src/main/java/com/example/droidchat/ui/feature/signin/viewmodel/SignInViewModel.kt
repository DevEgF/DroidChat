package com.example.droidchat.ui.feature.signin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.R
import com.example.droidchat.domain.model.exceptions.NetworkException
import com.example.droidchat.domain.repository.AuthRepository
import com.example.droidchat.ui.feature.signin.action.SignInActions
import com.example.droidchat.ui.feature.signin.event.SignInFormEvent
import com.example.droidchat.ui.feature.signin.state.SignInFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    private val _signInAction = MutableSharedFlow<SignInActions>()
    val signInActions = _signInAction.asSharedFlow()

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }
            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
        var isFormValid = true
        resetFormErrorState()
        if (formState.email.isBlank()) {
            formState = formState.copy(
                emailError = R.string.error_message_email_invalid
            )
            isFormValid = false
        }

        if (formState.password.isBlank()) {
            formState = formState.copy(
                passwordError = R.string.error_message_password_invalid
            )
            isFormValid = false
        }

        if (isFormValid) {
            formState = formState.copy(isLoading = true)

            viewModelScope.launch {
                authRepository.signIn(
                    userName = formState.email,
                    password = formState.password
                ).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)
                        emitActions(SignInActions.Success)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)

                        if (it is NetworkException.ApiException && it.statusCode == 401)
                            emitActions(SignInActions.Error.UnauthorizedError)
                        else emitActions(SignInActions.Error.GenericError)
                    }
                )
            }

        }
    }

    private fun resetFormErrorState() {
        formState = formState.copy(
            emailError = null,
            passwordError = null
        )
    }

    private fun emitActions(actions: SignInActions) {
        viewModelScope.launch {
            _signInAction.emit(actions)
        }
    }
}