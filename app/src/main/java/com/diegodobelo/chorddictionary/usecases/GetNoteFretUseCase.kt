package com.diegodobelo.chorddictionary.usecases

import com.diegodobelo.chorddictionary.models.amountOfNotes
import com.diegodobelo.chorddictionary.models.notesSymbols

// TODO: add HILT and inject repository, then get notesSymbols and stringsTune from repository
class GetNoteFretUseCase {

    operator fun invoke(noteSymbol: String, noteString: Int, stringsTune: Map<Int, String>): Int {

        val noteAbsVal = notesSymbols[noteSymbol] ?: 0
        val baseStringTune = notesSymbols[stringsTune[noteString]] ?: 0

        val notePos = noteAbsVal - baseStringTune
        return if (notePos < 0) {
            notePos + amountOfNotes
        } else {
            notePos
        }
    }
}