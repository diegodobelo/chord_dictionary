package com.diegodobelo.chorddictionary.models

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

        fun build(): ChordData {
            return ChordData(
                markers,
                notes
            )
        }
    }
}

//class FretBoard(
//    val fretBoardNumber: Number
//) {
//    private val _notes: MutableList<Note> = mutableListOf()
//    val notes: List<Note>
//        get() {
//            return _notes
//        }
//    fun addNote(note: Note) {
//        _notes.add(note)
//    }
//}

sealed class Note

object EmptyNote : Note()
data class Barre(
    val firstStringNumber: Int = 1,
    val lastStringNumber: Int = 6
) : Note()
data class FingerNote(
    val guitarStringNumber: Int,
    val fingerNumber: Int
) : Note()

data class MultipleFingerNotes(
    val notes: List<FingerNote>
) : Note()


sealed class MarkerType
object BassMarker : MarkerType()
object MuteMarker : MarkerType()
object NormalMarker : MarkerType()