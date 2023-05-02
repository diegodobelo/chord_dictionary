package com.diegodobelo.chorddictionary.ui.chord

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dot() {
    Canvas(modifier = Modifier.size(10.dp), onDraw = {
        drawCircle(color = Color.Black)
    })
}

@Composable
private fun HorizontalLine(
    lineColor: Color = Color.Black,
    strokeWidth: Dp = 2.dp
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
    strokeWidth: Dp = 2.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val canvasHeight = size.height
        drawLine(
            color = lineColor,
            start = Offset(x = 0f, y = canvasHeight),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = strokeWidth.toPx()
        )
    }
}

@Composable
private fun GuitarFret(
    totalHeight: Dp = 20.dp,
    fretWidth: Dp = 2.dp
) {
    Column(
        modifier = Modifier
            .height(totalHeight),
        verticalArrangement = Arrangement.Bottom
    ) {
        HorizontalLine(
            strokeWidth = fretWidth
        )
    }
}

@Composable
private fun GuitarFrets() {
    Column {
        GuitarFret(
            totalHeight = 0.dp,
            fretWidth = 8.dp
        )
        GuitarFret()
        GuitarFret()
        GuitarFret()
        GuitarFret()
        GuitarFret()
    }
}

@Composable
fun GuitarString(
    totalWidth: Dp = 20.dp,
    stringWidth: Dp = 2.dp
) {
    Row(
        modifier = Modifier
            .width(totalWidth),
        horizontalArrangement = Arrangement.End
    ) {
        VerticalLine(
            strokeWidth = stringWidth
        )
    }
}

@Composable
fun GuitarStrings() {
    Row {
        GuitarString(totalWidth = 0.dp)
        GuitarString()
        GuitarString()
        GuitarString()
        GuitarString()
        GuitarString()
    }
}

@Composable
fun GuitarArm() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        GuitarStrings()
        GuitarFrets()
    }
}

@Preview(showBackground = true)
@Composable
fun ChordPreview() {
    GuitarArm()
}