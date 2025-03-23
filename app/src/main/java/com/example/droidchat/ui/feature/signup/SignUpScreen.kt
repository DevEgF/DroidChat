package com.example.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droidchat.R
import com.example.droidchat.ui.component.PrimaryButton
import com.example.droidchat.ui.component.ProfilePictureOptionsModalBottomSheet
import com.example.droidchat.ui.component.ProfilePictureSelector
import com.example.droidchat.ui.component.SecondaryTextField
import com.example.droidchat.ui.feature.signup.event.SignUpFormEvent
import com.example.droidchat.ui.feature.signup.state.SignUpFormState
import com.example.droidchat.ui.feature.signup.viewmodel.SignUpFormValidator
import com.example.droidchat.ui.feature.signup.viewmodel.SignUpViewModel
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRouteRoute(
    viewModel: SignUpViewModel = viewModel {
        SignUpViewModel(SignUpFormValidator())
    }
) {
    val formState = viewModel.formState

    SignUpRouteScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpRouteScreen(
    formState: SignUpFormState,
    onFormEvent: (SignUpFormEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(56.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Spacer(Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .imePadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelector(
                        imageUri = formState.profilePictureUri,
                        modifier = Modifier.clickable {
                            onFormEvent(SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet)
                        }
                    )
                    Spacer(Modifier.height(30.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_first_name),
                        value = formState.firstName,
                        onValueChanged = { onFormEvent(SignUpFormEvent.FirstNameChanged(it)) },
                        errorText = formState.firstNameError?.let {
                            stringResource(
                                id = it,
                                stringResource(id = R.string.feature_sign_up_first_name)
                            )
                        },
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_last_name),
                        value = formState.lastName,
                        onValueChanged = { onFormEvent(SignUpFormEvent.LastNameChanged(it)) },
                        errorText = formState.lastNameError?.let {
                            stringResource(
                                it,
                                stringResource(id = R.string.feature_sign_up_last_name)
                            )
                        },
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_email),
                        value = formState.email,
                        onValueChanged = { onFormEvent(SignUpFormEvent.EmailChanged(it)) },
                        keyboardType = KeyboardType.Email,
                        errorText = formState.emailError?.let { stringResource(it) },
                    )
                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password),
                        value = formState.password,
                        onValueChanged = { onFormEvent(SignUpFormEvent.PasswordChanged(it)) },
                        keyboardType = KeyboardType.Password,
                        extraText = formState.passwordsMatchText,
                        errorText = formState.passwordError?.let { stringResource(it) },
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password_confirmation),
                        value = formState.passwordConfirmation,
                        onValueChanged = { onFormEvent(SignUpFormEvent.PasswordConfirmation(it)) },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        extraText = formState.passwordsMatchText,
                        errorText = formState.passwordConfirmationError?.let { stringResource(it) },
                    )
                    Spacer(Modifier.height(36.dp))
                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        onClick = { onFormEvent(SignUpFormEvent.Submit) }
                    )
                }
            }
            if (formState.isProfilePictureModalBottomSheetOpen) {
                ProfilePictureOptionsModalBottomSheet(
                    onDismissRequest = {
                        onFormEvent(SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                    },
                    onPictureSelected = { uri ->
                        onFormEvent(SignUpFormEvent.ProfilePhotoUriChanged(uri))

                        scope.launch {
                            sheetState.hide()
                        }
                    },
                    sheetState = sheetState,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpRouteScreenPreview() {
    DroidChatTheme {
        SignUpRouteScreen(
            formState = SignUpFormState(),
            onFormEvent = {}
        )
    }
}