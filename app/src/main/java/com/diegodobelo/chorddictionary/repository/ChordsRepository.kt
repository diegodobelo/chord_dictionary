package com.diegodobelo.chorddictionary.repository

import com.diegodobelo.chorddictionary.models.Barre
import com.diegodobelo.chorddictionary.models.BassMarker
import com.diegodobelo.chorddictionary.models.ChordData
import com.diegodobelo.chorddictionary.models.EmptyNote
import com.diegodobelo.chorddictionary.models.FingerNote
import com.diegodobelo.chorddictionary.models.MultipleFingerNotes
import com.diegodobelo.chorddictionary.models.MuteMarker
import com.diegodobelo.chorddictionary.models.NormalMarker
import com.diegodobelo.chorddictionary.models.Note

class ChordsRepository {
    companion object {
        val TEST_CHORD = buildTestChord()
        fun buildTestChord(): ChordData {
            val testChordBuilder = ChordData.Builder()
            testChordBuilder
                .addMarker(MuteMarker)
                .addMarker(BassMarker)
                .addMarker(NormalMarker)
                .addMarker(NormalMarker)
                .addMarker(NormalMarker)
                .addMarker(NormalMarker)
            testChordBuilder
                .addNote(Barre(1, 6))
                .addNote(FingerNote(2, 1))
                .addNote(
                    MultipleFingerNotes(
                        listOf(
                            FingerNote(3, 2),
                            FingerNote(4, 3),
//                            FingerNote(5, 4)
                        )
                    )
                )
                .addNote(FingerNote(5, 3))
                .addNote(FingerNote(5, 3))
                .addNote(EmptyNote)
            return testChordBuilder.build()
        }
//        val C_MAJOR_CHORD = ChordData(
//            markers = listOf(
//                MuteMarker,
//                BassMarker,
//                NormalMarker,
//                NormalMarker,
//                NormalMarker,
//                NormalMarker
//            ),
//            notes = listOf(
//                Barre(4, 6),
//                FingerNote(2, 1),
//                FingerNote(4, 2),
//                FingerNote(5, 3),
//                EmptyNote,
//                EmptyNote,
//            )
//        )
//
//        val TEST_CHORD = ChordData(
//            markers = listOf(
//                MuteMarker,
//                BassMarker,
//                NormalMarker,
//                NormalMarker,
//                NormalMarker,
//                NormalMarker
//            ),
//            notes = listOf(
//                FingerNote(1, 1),
//                FingerNote(2, 2),
//                FingerNote(3, 3),
//                FingerNote(4, 4),
//                FingerNote(5, 5),
//                FingerNote(6, 6),
//            )
//        )
    }
}