package com.diegodobelo.chorddictionary.usecases

import com.diegodobelo.chorddictionary.models.BassMarker
import com.diegodobelo.chorddictionary.models.ChordData
import com.diegodobelo.chorddictionary.models.EmptyNote
import com.diegodobelo.chorddictionary.models.FingerNote
import com.diegodobelo.chorddictionary.models.MultipleFingerNotes
import com.diegodobelo.chorddictionary.models.NormalMarker
import com.diegodobelo.chorddictionary.models.Note
import kotlin.math.abs

class CreateChordUseCase {

    operator fun invoke(
        baseStringNumber: Int,
        chordType: ChordType,
        extraNotes: List<Interval>
    ) : ChordData {
        val chordDataBuilder = ChordData.Builder()
        val baseNote = createFundamentalNote(baseStringNumber)
        val majorThird = createMajorThirds(baseStringNumber)
        val majorFifth = createMajorFifths(baseStringNumber)
        val allNotes = mutableListOf<Note>(baseNote).apply {
            addAll(majorFifth)
        }
        val normalizedNotes = normalizeNotes(allNotes)
        val allFretsNotes = disposeNotesInFrets(normalizedNotes)

        chordDataBuilder.addNotes(allFretsNotes)

        chordDataBuilder
            .addMarker(BassMarker)
            .addMarker(NormalMarker)
            .addMarker(NormalMarker)
            .addMarker(NormalMarker)
            .addMarker(NormalMarker)
            .addMarker(NormalMarker)

        return chordDataBuilder.build()
    }

    private fun normalizeNotes(notes: List<Note>): List<Note> {
        val fingerNotes = notes
            .filterIsInstance<FingerNote>()
            .sortedBy { it.relativeFretNumber }
        if (fingerNotes.isEmpty()) return notes
        val firstNote = fingerNotes[0]
        val normalizedOffset = abs(firstNote.relativeFretNumber)

        return fingerNotes.mapIndexed { index, note ->
            note.copy(
                relativeFretNumber = note.relativeFretNumber + normalizedOffset,
                fingerNumber = index + 1
            )
        }
    }

    private fun disposeNotesInFrets(notes: List<Note>): List<Note> {
        val allFretsNotes = mutableListOf<Note>()
        (0 until FRET_BOARD_SIZE).forEach { fretNumber ->
            val notesInFret = notes
                .filterIsInstance<FingerNote>()
                .filter {
                    it.relativeFretNumber == fretNumber
                }
            if (notesInFret.isEmpty()) {
                allFretsNotes.add(EmptyNote)
            } else {
                allFretsNotes.add(MultipleFingerNotes(notesInFret))
            }
        }
        return allFretsNotes
    }

    private fun createFundamentalNote(
        baseStringNumber: Int
    ) = FingerNote(guitarStringNumber = baseStringNumber, relativeFretNumber = 0)

    private fun createMajorThirds(
        baseStringNumber: Int
    ): List<Note> {
        val notes = mutableListOf<Note>()
        when (baseStringNumber) {
            SIXTH_STRING -> notes.addAll(MAJOR_THIRDS_FOR_SIXTH_STRING)
            FIFTH_STRING -> notes.addAll(MAJOR_THIRDS_FOR_FIFTH_STRING)
            FORTH_STRING -> notes.addAll(MAJOR_THIRDS_FOR_FORTH_STRING)
            THIRD_STRING -> notes.addAll(MAJOR_THIRDS_FOR_THIRD_STRING)
            SECOND_STRING -> notes.addAll(MAJOR_THIRDS_FOR_SECOND_STRING)
        }
        return notes
    }

    private fun createMajorFifths(
        baseStringNumber: Int
    ): List<Note> {
        val notes = mutableListOf<Note>()
        when (baseStringNumber) {
            SIXTH_STRING -> notes.addAll(MAJOR_FIFTH_FOR_SIXTH_STRING)
            FIFTH_STRING -> notes.addAll(MAJOR_FIFTH_FOR_FIFTH_STRING)
            FORTH_STRING -> notes.addAll(MAJOR_FIFTH_FOR_FORTH_STRING)
            THIRD_STRING -> notes.addAll(MAJOR_FIFTH_FOR_THIRD_STRING)
            SECOND_STRING -> notes.addAll(MAJOR_FIFTH_FOR_SECOND_STRING)
        }
        return notes
    }

    companion object {
        const val FRET_BOARD_SIZE = 6
        const val SIXTH_STRING = 6
        const val FIFTH_STRING = 5
        const val FORTH_STRING = 4
        const val THIRD_STRING = 3
        const val SECOND_STRING = 2
        // TODO: move this to repository
        val MAJOR_THIRDS_FOR_SIXTH_STRING = listOf(
            FingerNote(guitarStringNumber = 5, relativeFretNumber = -1),
            FingerNote(guitarStringNumber = 3, relativeFretNumber = +1),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = -3)
        )
        val MAJOR_THIRDS_FOR_FIFTH_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = -1),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = -3)
        )
        val MAJOR_THIRDS_FOR_FORTH_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 5, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 3, relativeFretNumber = -1),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = +2)
        )
        val MAJOR_THIRDS_FOR_THIRD_STRING = listOf(
            FingerNote(guitarStringNumber = 5, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = +0)
        )
        val MAJOR_THIRDS_FOR_SECOND_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = -1),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = +1),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = -1)
        )


        val MAJOR_FIFTH_FOR_SIXTH_STRING = listOf(
            FingerNote(guitarStringNumber = 5, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = +0)
        )
        val MAJOR_FIFTH_FOR_FIFTH_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = +0),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 3, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = +0)
        )
        val MAJOR_FIFTH_FOR_FORTH_STRING = listOf(
            FingerNote(guitarStringNumber = 5, relativeFretNumber = +0),
            FingerNote(guitarStringNumber = 3, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = -2),
        )
        val MAJOR_FIFTH_FOR_THIRD_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = -2),
            FingerNote(guitarStringNumber = 4, relativeFretNumber = +0),
            FingerNote(guitarStringNumber = 2, relativeFretNumber = +3),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = -2)
        )
        val MAJOR_FIFTH_FOR_SECOND_STRING = listOf(
            FingerNote(guitarStringNumber = 6, relativeFretNumber = +2),
            FingerNote(guitarStringNumber = 5, relativeFretNumber = -3),
            FingerNote(guitarStringNumber = 3, relativeFretNumber = -1),
            FingerNote(guitarStringNumber = 1, relativeFretNumber = +2)
        )
    }
}

// TODO: move to models
sealed class ChordType {
    object Major : ChordType()
    object Minor : ChordType()
    object Suspended : ChordType()
}

sealed class Interval {
    object SecondMinor : Interval()
    object Second : Interval()
    object Third : Interval()
    object Forth : Interval()
    object DiminishedFifth : Interval()
    object Fifth : Interval()
    object SixthMinor : Interval()
    object SixthMajor : Interval()
    object Seventh : Interval()
    object SeventhMajor : Interval()
    object Eighth : Interval()
    object NinthMinor : Interval()
    object Ninth : Interval()
}