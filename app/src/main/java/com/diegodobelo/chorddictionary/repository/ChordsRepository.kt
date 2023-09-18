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

        val MAJOR_TEMPLATE_1 = ChordData(
            markers = listOf(
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 5),
                FingerNote(2, 2),
                FingerNote(4, 3),
                FingerNote(5, 4, true),
                EmptyNote,
                EmptyNote
            )
        )

        val C_MAJOR_CHORD = ChordData(
            markers = listOf(
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(4, 6),
                FingerNote(2, 1),
                FingerNote(4, 2),
                FingerNote(5, 3),
                EmptyNote,
                EmptyNote,
            )
        )

        val TEST_CHORD = ChordData(
            markers = listOf(
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                FingerNote(1, 1),
                FingerNote(2, 2),
                FingerNote(3, 3),
                FingerNote(4, 4),
                FingerNote(5, 5),
                FingerNote(6, 6),
            )
        )
    }
}