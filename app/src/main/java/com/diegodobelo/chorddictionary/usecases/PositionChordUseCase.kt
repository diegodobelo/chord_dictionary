package com.diegodobelo.chorddictionary.usecases

import com.diegodobelo.chorddictionary.models.ChordData

class PositionChordUseCase {

    operator fun invoke(chordData: ChordData, basePosOnString: Int): ChordData {
        val baseNote = chordData.notes.lastOrNull { it.isBaseNote }
        val baseNotePos = chordData.notes.indexOf(baseNote)
        if (baseNotePos < 0) return chordData
        val offset = basePosOnString - baseNotePos
        val chordDataBuilder = ChordData.Builder().copyChord(chordData)
        chordDataBuilder.shiftNotes(offset)
        return chordDataBuilder.build()
    }
}
