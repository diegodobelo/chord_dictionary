package com.diegodobelo.chorddictionary.usecases

import com.diegodobelo.chorddictionary.models.amountOfNotes
import com.diegodobelo.chorddictionary.models.notesSymbols
import javax.inject.Inject

// TODO: add HILT and inject repository, then get notesSymbols and stringsTune from repository
class GetFretPositionUseCase @Inject constructor() {

    operator fun invoke(noteSymbol: String, noteString: Int, stringsTune: Map<Int, String>): Int {

        val noteAbsVal = notesSymbols[noteSymbol] ?: 0
        val baseStringTune = notesSymbols[stringsTune[noteString]] ?: 0

        val notePos = noteAbsVal - baseStringTune
        return if (notePos < 0) {
            notePos + amountOfNotes
        } else {
            notePos
        } - 1 // Make it 0 based
    }
}