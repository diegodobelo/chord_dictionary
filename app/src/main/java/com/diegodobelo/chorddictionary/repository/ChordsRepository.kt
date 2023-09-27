package com.diegodobelo.chorddictionary.repository

import com.diegodobelo.chorddictionary.models.ChordData
import com.diegodobelo.chorddictionary.usecases.GetFretPositionUseCase
import com.diegodobelo.chorddictionary.usecases.PositionChordUseCase
import javax.inject.Inject

class ChordsRepository @Inject constructor(
    val fretPositionUseCase: GetFretPositionUseCase,
    val positionChordUseCase: PositionChordUseCase
) {

    private val stringsTune = mapOf(
        1 to "E",
        2 to "B",
        3 to "G",
        4 to "D",
        5 to "A",
        6 to "E"
    )

    fun getMajorChordsForTune(tune: String, chordTemplate: ChordData): ChordData {
        val guitarStringOfBaseNote = chordTemplate.getGuitarStringOfBaseNote()
        val fretPos = fretPositionUseCase(tune, guitarStringOfBaseNote, stringsTune)

        return positionChordUseCase(chordTemplate, fretPos)
    }
}