package com.diegodobelo.chorddictionary.usecases

import com.diegodobelo.chorddictionary.models.ChordData

class PositionChordUseCase {

    operator fun invoke(chordData: ChordData, newLastNotePosition: Int): ChordData {
        val baseNotePosition = chordData.baseNotePosition()
        val lastNotePosition = chordData.lastNotePosition()

        val baseToLastOffset = lastNotePosition - baseNotePosition

        if (lastNotePosition < 0) return chordData
        val offset = (newLastNotePosition - lastNotePosition) + baseToLastOffset
        val chordDataBuilder = ChordData.Builder().copyChord(chordData)
        chordDataBuilder.shiftNotes(offset)
        return chordDataBuilder.build()
    }
}
