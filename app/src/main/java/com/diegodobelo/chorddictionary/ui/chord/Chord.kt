package com.diegodobelo.chorddictionary.ui.chord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArmWithMarkers(
    markers: List<MarkerType>
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        GuitarArm()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            markers.forEach {
                MarkerForType(markerType = it)
            }
        }

    }
}

@Composable
fun MarkerForType(
    markerType: MarkerType
) {
    when(markerType) {
        BassMarker -> BassStringMarker()
        MuteMarker -> UnplayableStringMarker()
        NormalMarker -> NormalStringMarker()
    }
}

sealed class MarkerType

object BassMarker : MarkerType()
object MuteMarker : MarkerType()
object NormalMarker : MarkerType()

@Preview(showBackground = true)
@Composable
fun ArmWithMarkersPreview() {
    ArmWithMarkers(
        listOf(
            MuteMarker,
            BassMarker,
            NormalMarker,
            NormalMarker,
            NormalMarker,
            NormalMarker
        )
    )
}