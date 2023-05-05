package com.diegodobelo.chorddictionary.ui.chord

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private fun getVerticalGradientWithTransparentEnds(
    color: Color
): Brush {
    val colorStops = arrayOf(
        0.0f to Color.Transparent,
        0.2f to color,
        0.8f to color,
        1f to Color.Transparent
    )
    return Brush.verticalGradient(colorStops = colorStops)
}

@Composable
private fun HorizontalLine(
    lineColor: Color = Color.Black,
    strokeWidth: Dp = 1.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val canvasWidth = size.width
        drawLine(
            color = lineColor,
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = strokeWidth.toPx()
        )
    }
}

@Composable
private fun VerticalLine(
    lineColor: Color = Color.Black,
    strokeWidth: Dp = 1.dp,
    showTransparentEnds: Boolean = false
) {
    val brush = if (showTransparentEnds) {
        getVerticalGradientWithTransparentEnds(lineColor)
    } else {
        Brush.verticalGradient(listOf(lineColor, lineColor))
    }
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val canvasHeight = size.height
        drawLine(
            brush = brush,
            start = Offset(x = 0f, y = canvasHeight),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = strokeWidth.toPx()
        )
    }
}

@Composable
private fun GuitarFret(
    fretWidth: Dp = 1.dp,
    paddingBottom: Dp = 0.dp,
    isTransparentFret: Boolean = false
) {
    Column(
        modifier = Modifier.padding(bottom = paddingBottom)
    ) {
        HorizontalLine(
            strokeWidth = fretWidth,
            lineColor = if (isTransparentFret) Color.Transparent else Color.Black
        )
    }
}

@Composable
private fun GuitarFrets(
    showFirstFret: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (showFirstFret) GuitarFret(
            fretWidth = 8.dp,
            paddingBottom = 4.dp
        )
        GuitarFret(isTransparentFret = !showFirstFret)
        GuitarFret()
        GuitarFret()
        GuitarFret()
        GuitarFret()
        GuitarFret(isTransparentFret = true)
    }
}

@Composable
fun GuitarString(
    stringWidth: Dp = 1.dp,
    showFadedEnds: Boolean = false
) {
    Row(
    ) {
        VerticalLine(
            strokeWidth = stringWidth,
            showTransparentEnds = showFadedEnds
        )
    }
}

@Composable
fun GuitarStrings(
    isShowingFirstFret: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat((0..5).count()) {
            GuitarString(
                showFadedEnds = !isShowingFirstFret
            )
        }
    }
}

@Composable
fun GuitarNeck() {
    val showFirstFret = true
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(100.dp)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        GuitarStrings(isShowingFirstFret = showFirstFret)
        GuitarFrets(showFirstFret = showFirstFret)
    }
}

@Preview(showBackground = true)
@Composable
fun ChordPreview() {
    Note(2)
}