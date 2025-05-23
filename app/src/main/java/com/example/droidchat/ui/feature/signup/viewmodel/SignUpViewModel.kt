package com.example.droidchat.ui.feature.signup.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.R
import com.example.droidchat.core.image.ImageCompressor
import com.example.droidchat.core.image.ImageCompressorImpl
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.exceptions.NetworkException
import com.example.droidchat.domain.repository.AuthRepository
import com.example.droidchat.ui.feature.validator.FormValidator
import com.example.droidchat.ui.feature.signup.event.SignUpFormEvent
import com.example.droidchat.ui.feature.signup.state.SignUpFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpFormState>,
    private val authRepository: AuthRepository,
    private val imageCompressor: ImageCompressor,
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
                event.uri?.let {
                    compressImageAndUpdateState(it)
                }
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

    fun errorMessageShow() {
        formState = formState.copy(apiErrorMessageResId = null)
    }

    private fun compressImageAndUpdateState(uri: Uri) {
        viewModelScope.launch {
            try {
                formState = formState.copy(isCompressingImage = true)
                val compressedFile = imageCompressor.compressAndResizeImage(uri)
                formState = formState.copy(profilePictureUri = compressedFile.toUri())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                formState = formState.copy(isCompressingImage = false)
            }
        }
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                var profilePictureId: Int? = null

                var errorWhenUploadingProfilePicture = false

                formState.profilePictureUri?.path?.let { path ->
                    authRepository.uploadProfilePicture(path).fold(
                        onSuccess = { image ->
                            profilePictureId = image.id
                        },
                        onFailure = {
                            formState = formState.copy(
                                isLoading = false,
                                profilePictureUri = null,
                                apiErrorMessageResId = R.string.error_message_profile_picture_uploading_failed
                            )
                            errorWhenUploadingProfilePicture = true
                        }
                    )
                }

                if (errorWhenUploadingProfilePicture) return@launch
                
                authRepository.signUp(
                    createAccount = CreateAccount(
                        username = formState.email,
                        password = formState.password,
                        firstName = formState.firstName,
                        lastName = formState.lastName,
                        profilePictureId = profilePictureId,
                    )
                ).fold(onSuccess = {
                    formState = formState.copy(
                        isLoading = false,
                        isSignedUp = true
                    )
                }, onFailure = {
                    formState = formState.copy(
                        isLoading = false,
                        apiErrorMessageResId = if (it is NetworkException.ApiException) {
                            when (it.statusCode) {
                                400 -> R.string.error_message_api_form_validation_failed
                                409 -> R.string.error_message_user_with_username_already_exists
                                else -> R.string.common_generic_error_title
                            }
                        } else R.string.common_generic_error_message
                    )
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
