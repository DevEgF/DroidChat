package com.example.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.droidchat.ui.component.PrimaryTextField
import com.example.droidchat.ui.theme.BackgroundGradient

@Composable
fun SignInRoute() {
    SignScreen()
}

@Composable
fun SignScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(64.dp))

        var email by remember {
            mutableStateOf("")
        }

        PrimaryTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = stringResource(R.string.feature_login_email),
            modifier = Modifier
                .padding(horizontal = 16.dp),
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
        )

        Spacer(modifier = Modifier.height(16.dp))

        var password by remember {
            mutableStateOf("")
        }

        PrimaryTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = stringResource(R.string.feature_login_password),
            modifier = Modifier
                .padding(horizontal = 16.dp),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(16.dp))

        var isLoading by remember {
            mutableStateOf(false)
        }

        PrimaryButton(
            text = stringResource(R.string.feature_login_button),
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                isLoading = !isLoading
            },
            isLoading = isLoading
        )
    }
}

@Preview
@Composable
private fun SignScreenPreview() {
    SignScreen()
}