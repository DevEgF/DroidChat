package com.example.droidchat.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .height(64.dp),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        )
    ) {
        Box(
            modifier = Modifier
                .animateContentSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(30.dp)
                        .aspectRatio(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeCap = StrokeCap.Round
                )
            } else Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    DroidChatTheme {
        PrimaryButton(
            text = "Sign In",
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonLoadingPreview() {
    DroidChatTheme {
        PrimaryButton(
            text = "Sign In",
            onClick = {},
            isLoading = true
        )
    }
}