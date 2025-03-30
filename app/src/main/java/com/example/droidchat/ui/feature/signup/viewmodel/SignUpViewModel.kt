package com.example.droidchat.ui.feature.signup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.R
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.repository.AuthRepository
import com.example.droidchat.ui.feature.validator.FormValidator
import com.example.droidchat.ui.feature.signup.event.SignUpFormEvent
import com.example.droidchat.ui.feature.signup.state.SignUpFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpFormState>,
    private val authRepository: AuthRepository
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }

            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }

            is SignUpFormEvent.LastNameChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                val matchText = updatePasswordState(
                    password = event.password, confirmation = formState.passwordConfirmation
                )

                formState = formState.copy(
                    password = event.password, passwordsMatchText = matchText
                )
            }

            is SignUpFormEvent.PasswordConfirmation -> {
                val matchText = updatePasswordState(
                    password = formState.password, confirmation = event.passwordConfirmation
                )

                formState = formState.copy(
                    passwordConfirmation = event.passwordConfirmation,
                    passwordsMatchText = matchText
                )
            }

            SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }

            SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }

            SignUpFormEvent.Submit -> doSignUp()
        }
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                authRepository.signUp(
                    createAccount = CreateAccount(
                        username = formState.email,
                        password = formState.password,
                        firstName = formState.firstName,
                        lastName = formState.lastName,
                        profilePictureId = null,
                    )
                ).fold(onSuccess = {
                    formState = formState.copy(isLoading = false)
                }, onFailure = {
                    formState = formState.copy(isLoading = false)
                })
            }
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

    private fun updatePasswordState(password: String, confirmation: String): Int? {
        return if (password.isNotEmpty() && password == confirmation) {
            R.string.feature_sign_up_passwords_match
        } else {
            null
        }
    }
}