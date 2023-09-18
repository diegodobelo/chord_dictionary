package com.diegodobelo.chorddictionary.ui.chord

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Note(
    fingerNumber: Int,
    circleColor: Color = Color.Black
) {
    Box {
        Canvas(modifier = Modifier.size(12.dp), onDraw = {
            drawCircle(color = circleColor)
        })
        Text(
            text = fingerNumber.toString(),
            modifier = Modifier
                .align(Alignment.Center),
            color = Color.White,
            fontSize = 8.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun BarreNote() {
    Box {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp)
                .height(8.dp),
            onDraw = {
                drawRoundRect(
                    color = Color.Black,
                    cornerRadius = CornerRadius(4.dp.toPx())
                )
            }
        )
    }
}

@Composable
private fun PlayableStringMarker(
    isFill: Boolean
) {
    Canvas(modifier = Modifier.size(8.dp), onDraw = {
        drawCircle(
            color = Color.Black,
            style = if (isFill) Fill else Stroke(width = 1.dp.toPx())
        )
    })
}

@Composable
fun UnplayableStringMarker() {
    Canvas(
        modifier = Modifier
            .width(8.dp)
            .height(8.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            color = Color.Black,
            start = Offset(x = canvasWidth, y = canvasHeight),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = 1.dp.toPx()
        )
        drawLine(
            color = Color.Black,
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            strokeWidth = 1.dp.toPx()
        )
    }
}

@Composable
fun BassStringMarker() {
    PlayableStringMarker(isFill = true)
}

@Composable
fun NormalStringMarker() {
    PlayableStringMarker(isFill = false)
}

@Preview(showBackground = true)
@Composable
fun MarkersPreview() {
    BarreNote()
}