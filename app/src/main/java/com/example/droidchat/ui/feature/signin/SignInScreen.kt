package com.example.droidchat.ui.feature.signin

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droidchat.R
import com.example.droidchat.ui.component.PrimaryButton
import com.example.droidchat.ui.component.PrimaryTextField
import com.example.droidchat.ui.feature.signin.event.SignInFormEvent
import com.example.droidchat.ui.feature.signin.state.SignInFormState
import com.example.droidchat.ui.feature.signin.viewmodel.SignInViewModel
import com.example.droidchat.ui.theme.BackgroundGradient

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel(),
    navigateToSignUp: () -> Unit
) {
    val formState = viewModel.formState
    SignScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent,
        onRegisterClick = navigateToSignUp
    )
}

@Composable
fun SignScreen(
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(78.dp))

        PrimaryTextField(
            value = formState.email,
            onValueChange = {
                onFormEvent(SignInFormEvent.EmailChanged(it))
            },
            placeholder = stringResource(R.string.feature_login_email),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            errorMessage = formState.emailError?.let { stringResource(id = it) },
        )

        Spacer(modifier = Modifier.height(14.dp))

        PrimaryTextField(
            value = formState.password,
            onValueChange = {
                onFormEvent(SignInFormEvent.PasswordChanged(it))
            },
            placeholder = stringResource(R.string.feature_login_password),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            errorMessage = formState.passwordError?.let { stringResource(id = it) },
        )

        Spacer(modifier = Modifier.height(98.dp))

        PrimaryButton(
            text = stringResource(R.string.feature_login_button),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            onClick = {
                onFormEvent(SignInFormEvent.Submit)
            },
            isLoading = formState.isLoading
        )

        Spacer(modifier = Modifier.height(56.dp))

        val noAccountText = stringResource(R.string.feature_login_no_account)
        val registerText = stringResource(R.string.feature_login_register)

        val noAccountRegisterText = "${noAccountText} ${registerText}"

        val annotatedString = buildAnnotatedString {
            val registerTextStartIndex = noAccountRegisterText.indexOf(registerText)
            val registerTextEndIndex = registerTextStartIndex + registerText.length

            append(noAccountRegisterText)

            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    textDecoration = null
                ),
                start = 0,
                end = registerTextStartIndex
            )

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = "register text.",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = {
                        onRegisterClick()
                    }
                ),
                start = registerTextStartIndex,
                end = registerTextEndIndex
            )
        }

        Text(annotatedString)
    }
}

@Preview
@Composable
private fun SignScreenPreview() {
    SignScreen(
        formState = SignInFormState(),
        onFormEvent = {},
        onRegisterClick = {},
    )
}