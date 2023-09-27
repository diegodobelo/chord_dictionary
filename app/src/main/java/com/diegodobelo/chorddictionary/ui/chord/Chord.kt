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
import com.diegodobelo.chorddictionary.models.ChordData
import com.diegodobelo.chorddictionary.models.EmptyNote
import com.diegodobelo.chorddictionary.models.MarkerType
import com.diegodobelo.chorddictionary.models.MuteMarker
import com.diegodobelo.chorddictionary.models.NormalMarker
import com.diegodobelo.chorddictionary.models.NoteOnString
import com.diegodobelo.chorddictionary.models.NotesOnFret
import com.diegodobelo.chorddictionary.templates.MajorTemplates

const val STRINGS_COUNT = 6
const val FRETS_COUNT = 6

@Composable
fun Chord(chordData: ChordData, visibleFrets: Int) {
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        NeckWithMarkers(markers = chordData.markers, chordData.fretPosition, visibleFrets)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 18.dp, top = 5.dp, end = 2.dp, bottom = 18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            chordData.notes.forEach {
                when(it) {
                    is Barre -> HorizontallyPositionedBarre(barre = it)
                    is NoteOnString -> HorizontallyPositionedNotes(noteOnStrings = listOf(it))
                    is NotesOnFret -> HorizontallyPositionedNotes(noteOnStrings = it.notes)
                    EmptyNote -> Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
@Composable
private fun HorizontallyPositionedNotes(
    noteOnStrings: List<NoteOnString>,
    stringsCount: Int = STRINGS_COUNT
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (stringsCount downTo 1).forEach { currentStringNumber ->
            val fingerNote = noteOnStrings.firstOrNull { it.guitarStringNumber == currentStringNumber}
            if (fingerNote != null) {
                Note(
                    fingerNumber = fingerNote.fingerNumber
                )
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
    markers: List<MarkerType>,
    lastNotePosition: Int,
    visibleFrets: Int
) {
    val fretNumber = if (lastNotePosition > visibleFrets) visibleFrets else lastNotePosition
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
    ) {
        Row {
            FretPositionOnNeck(fretNumber, lastNotePosition + 1)
            GuitarNeck()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            markers.forEach {
                MarkerForType(markerType = it)
            }
        }
    }
}

/**
 * Show a number on the right side of the fretboard.
 * @param fretNumber the vertical position where "fretPosition" will be shown. Starts at 0
 * @param fretPosition the number (value) that will be shown on the right side of the fretboard
 */
@Composable
fun FretPositionOnNeck(fretNumber: Int, fretPosition: Int) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .padding(top = 4.dp)
    ) {
        repeat(FRETS_COUNT) { fret ->
            if (fretNumber == fret) {
                FretPosition(fretPosition)
            } else {
                Spacer(modifier = Modifier.height(16.dp))
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
    Chord(MajorTemplates.MAJOR_TEMPLATE_1, 6)
}