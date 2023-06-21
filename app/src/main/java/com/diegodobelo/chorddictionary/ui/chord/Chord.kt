package com.diegodobelo.chorddictionary.ui.chord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diegodobelo.chorddictionary.models.Barre
import com.diegodobelo.chorddictionary.models.BassMarker
import com.diegodobelo.chorddictionary.models.EmptyNote
import com.diegodobelo.chorddictionary.models.FingerNote
import com.diegodobelo.chorddictionary.models.MarkerType
import com.diegodobelo.chorddictionary.models.MultipleFingerNotes
import com.diegodobelo.chorddictionary.models.MuteMarker
import com.diegodobelo.chorddictionary.models.NormalMarker
import com.diegodobelo.chorddictionary.repository.ChordsRepository
import com.diegodobelo.chorddictionary.usecases.ChordType
import com.diegodobelo.chorddictionary.usecases.CreateChordUseCase

const val STRINGS_COUNT = 6

@Composable
fun Chord() {
    val createChordUseCase = CreateChordUseCase()
    val testChord = createChordUseCase(
        baseStringNumber = CreateChordUseCase.FIFTH_STRING,
        chordType = ChordType.Major,
        extraNotes = emptyList()
    )
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        NeckWithMarkers(markers = testChord.markers)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 2.dp, top = 5.dp, end = 2.dp, bottom = 18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            testChord.notes.forEach {
                when(it) {
                    is Barre -> HorizontallyPositionedBarre(barre = it)
                    is FingerNote -> HorizontallyPositionedNotes(fingerNotes = listOf(it))
                    is MultipleFingerNotes -> HorizontallyPositionedNotes(fingerNotes = it.notes)
                    EmptyNote -> Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
@Composable
private fun HorizontallyPositionedNotes(
    fingerNotes: List<FingerNote>,
    stringsCount: Int = STRINGS_COUNT
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (stringsCount downTo 1).forEach { currentStringNumber ->
            val fingerNote = fingerNotes.firstOrNull { it.guitarStringNumber == currentStringNumber}
            if (fingerNote != null) {
                Note(fingerNumber = fingerNote.fingerNumber)
            } else {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
private fun HorizontallyPositionedBarre(
    barre: Barre,
    stringsCount: Int = STRINGS_COUNT
) {
    val minWeight = 0.01f
    val startSpaceWeight = stringsCount - barre.lastStringNumber + minWeight
    val endSpaceWeight = (barre.firstStringNumber - 1) + minWeight
    val barreSpaceWeight = barre.lastStringNumber - (barre.firstStringNumber - 1) + minWeight

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(startSpaceWeight))
        Box(modifier = Modifier.weight(barreSpaceWeight)) {
            BarreNote()
        }
        Spacer(modifier = Modifier.weight(endSpaceWeight))
    }
}

@Composable
fun NeckWithMarkers(
    markers: List<MarkerType>
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        GuitarNeck()
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

@Preview(showBackground = true)
@Composable
fun NeckWithMarkersPreview() {
    Chord()
}