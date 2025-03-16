package com.example.droidchat.ui.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.bottomBorder(color: Color, strokeWidth: Dp) = this.drawBehind {
    val strokeWidthPx = strokeWidth.toPx()

    val width = size.width
    val height = size.height

    drawLine(
        color = color,
        start = Offset(0f, height - strokeWidthPx),
        end = Offset(width, height - strokeWidthPx),
        strokeWidth = strokeWidthPx
    )
}