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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
fun Note(
    fingerNumber: Int
) {
    Box {
        Canvas(modifier = Modifier.size(14.dp), onDraw = {
            drawCircle(color = Color.Black)
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
private fun UnplayableStringMarker() {
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
        GuitarFret(isTransparentFret = !showFirstFret)
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
fun GuitarArm() {
    val showFirstFret = false
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