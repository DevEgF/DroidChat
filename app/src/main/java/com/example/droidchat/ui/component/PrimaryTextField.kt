package com.example.droidchat.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.ui.theme.ColorError
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: String = "",
    imeAction: ImeAction = ImeAction.Next,
    errorMessage: String? = null
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
            leadingIcon = {
                leadingIcon?.let { icon ->
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            trailingIcon = {
                if(keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                    val visibilityIcon = if(passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    Icon(
                        painter = painterResource(id = visibilityIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { passwordVisible = !passwordVisible },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            visualTransformation = if(keyboardType == KeyboardType.Password) {
                if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = if(errorMessage != null) ColorError else MaterialTheme.colorScheme.onSurfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
            )
        )

        errorMessage?.let { text ->
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp),
                color = ColorError
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryTextFieldPreview() {
    DroidChatTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            placeholder = "Email",
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
        )
    }
}