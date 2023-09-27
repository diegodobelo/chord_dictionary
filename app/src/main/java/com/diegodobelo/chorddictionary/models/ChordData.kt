package com.diegodobelo.chorddictionary.models

import kotlin.math.abs

data class ChordData(
    val markers: List<MarkerType>,
    val notes: List<Note>,
    val hasOverflownFrets: Boolean = false
) {

    fun lastNotePosition(): Int {
        return notes.indexOfLast { it !is EmptyNote }
    }

    fun baseNotePosition(): Int {
        return notes.indexOfLast { it.isBaseNote }
    }

    class Builder {
        private val markers: MutableList<MarkerType> = mutableListOf()
        private val notes: MutableList<Note> = mutableListOf()

        var hasOverflownFrets = false

        fun addMarker(markerType: MarkerType): Builder {
            markers.add(markerType)
            return this
        }

        fun addNote(note: Note): Builder {
            notes.add(note)
            return this
        }

        fun addNotes(note: List<Note>): Builder {
            notes.addAll(note)
            return this
        }

        private fun shiftNotesLeft() {
            notes.removeAt(0)
            notes.add(EmptyNote)
        }

        private fun shiftNotesRight() {
            if (notes.last() !is EmptyNote) {
                hasOverflownFrets = true
            } else {
                notes.add(0, EmptyNote)
                notes.removeLast()
            }
        }

        fun shiftNotes(amount: Int): Builder {
            if (amount > 0) {
                repeat(amount) {
                    shiftNotesRight()
                }
            }
            if (amount < 0) {
                repeat(abs(amount)) {
                    shiftNotesLeft()
                }
            }
            return this
        }

        fun build(): ChordData {
            return ChordData(
                markers,
                notes
            )
        }

        fun copyChord(chordData: ChordData): Builder {
            notes.addAll(chordData.notes)
            markers.addAll(chordData.markers)
            return this
        }
    }
}

const val UNDEFINED_FINGER_NUMBER = -1

// TODO: move to repository
const val amountOfNotes = 12
val notesSymbols = mapOf(
    "C"  to 0,
    "C#" to 1,
    "D♭" to 1,
    "D"  to 2,
    "D#" to 3,
    "E♭" to 3,
    "E"  to 4,
    "F♭" to 4,
    "F"  to 5,
    "F#" to 6,
    "G♭" to 6,
    "G"  to 7,
    "G#" to 8,
    "A♭" to 8,
    "A"  to 9,
    "A#" to 10,
    "B♭" to 10,
    "B"  to 11
)

val stringsTune = mapOf(
    1 to "E",
    2 to "B",
    3 to "G",
    4 to "D",
    5 to "A",
    6 to "E"
)

sealed class Note(val isBaseNote: Boolean = false)

object EmptyNote : Note()
data class Barre(
    val firstStringNumber: Int = 1,
    val lastStringNumber: Int = 6,
    val baseNote: Boolean = false
) : Note(baseNote)
data class NoteOnString(
    val guitarStringNumber: Int,
    val fingerNumber: Int = UNDEFINED_FINGER_NUMBER,
    val baseNote: Boolean = false
) : Note(baseNote)

data class NotesOnFret(
    var notes: List<NoteOnString>
) : Note(notes.any { it.isBaseNote })

sealed class MarkerType
object BassMarker : MarkerType()
object MuteMarker : MarkerType()
object NormalMarker : MarkerType()