package com.diegodobelo.chorddictionary.models

data class ChordData(
    val markers: List<MarkerType>,
    val notes: List<Note>
)

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


sealed class MarkerType
object BassMarker : MarkerType()
object MuteMarker : MarkerType()
object NormalMarker : MarkerType()