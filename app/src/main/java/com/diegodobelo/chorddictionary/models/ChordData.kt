package com.diegodobelo.chorddictionary.models

import kotlin.math.abs

data class ChordData(
    val markers: List<MarkerType>,
    val notes: List<Note>
) {
    class Builder {
        private val markers: MutableList<MarkerType> = mutableListOf()
        private val notes: MutableList<Note> = mutableListOf()

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
            notes.add(0, EmptyNote)
            notes.removeLast()
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

sealed class Note(val isBaseNote: Boolean = false)

object EmptyNote : Note()
data class Barre(
    val firstStringNumber: Int = 1,
    val lastStringNumber: Int = 6,
    val baseNote: Boolean = false
) : Note(baseNote)
data class FingerNote(
    val guitarStringNumber: Int,
    val fingerNumber: Int = UNDEFINED_FINGER_NUMBER,
    val baseNote: Boolean = false
) : Note(baseNote)

data class MultipleFingerNotes(
    val notes: List<FingerNote>
) : Note(notes.any { it.isBaseNote })


sealed class MarkerType
object BassMarker : MarkerType()
object MuteMarker : MarkerType()
object NormalMarker : MarkerType()