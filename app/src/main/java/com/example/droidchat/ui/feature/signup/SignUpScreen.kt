package com.example.droidchat.ui.feature.signup

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.droidchat.ui.component.ProfilePictureOptionsModalBottomSheet
import com.example.droidchat.ui.component.ProfilePictureSelector
import com.example.droidchat.ui.component.SecondaryTextField
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignUpRouteRoute() {
    SignUpRouteScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
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

            var profilePictureSelectedUri by remember {
                mutableStateOf<Uri?>(null)
            }

            var openProfilePictureOptionsModelBottomSheet by remember {
                mutableStateOf(false)
            }

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
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelector(
                        imageUri = profilePictureSelectedUri,
                        modifier = Modifier.clickable {
                            openProfilePictureOptionsModelBottomSheet = true
                        }
                    )
                    Spacer(Modifier.height(30.dp))
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

            if (openProfilePictureOptionsModelBottomSheet) {
                ProfilePictureOptionsModalBottomSheet(onDismissRequest = {
                    openProfilePictureOptionsModelBottomSheet = false
                })
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