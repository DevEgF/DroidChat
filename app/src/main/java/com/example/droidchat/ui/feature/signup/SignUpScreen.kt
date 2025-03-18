package com.example.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.ui.component.PrimaryButton
import com.example.droidchat.ui.component.SecondaryTextField
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.Surface

@Composable
fun SignUpRouteRoute() {
    SignUpRouteScreen()
}

@Composable
fun SignUpRouteScreen() {
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
                    modifier = Modifier.padding(16.dp)
                ) {
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_first_name),
                        value = "",
                        onValueChanged = {},
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_last_name),
                        value = "",
                        onValueChanged = {},
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_email),
                        value = "",
                        onValueChanged = {},
                        keyboardType = KeyboardType.Email
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password),
                        value = "",
                        onValueChanged = {},
                        keyboardType = KeyboardType.Password
                    )
                    Spacer(Modifier.height(22.dp))
                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password_confirmation),
                        value = "",
                        onValueChanged = {},
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                    Spacer(Modifier.height(36.dp))
                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpRouteScreenPreview() {
    DroidChatTheme {
        SignUpRouteScreen()
    }
}