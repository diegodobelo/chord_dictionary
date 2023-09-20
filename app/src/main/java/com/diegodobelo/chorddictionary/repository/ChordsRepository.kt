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

        // MAJOR CAGED
        // C
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
                FingerNote(2),
                FingerNote(4),
                FingerNote(5, baseNote = true),
                EmptyNote,
                EmptyNote
            )
        )

        // A
        val MAJOR_TEMPLATE_2 = ChordData(
            markers = listOf(
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 5, true),
                EmptyNote,
                MultipleFingerNotes(
                    listOf(
                        FingerNote(2),
                        FingerNote(3),
                        FingerNote(4)
                    )
                ),
                EmptyNote,
                EmptyNote,
                EmptyNote
            )
        )

        // G
        val MAJOR_TEMPLATE_3 = ChordData(
            markers = listOf(
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 6, true),
                EmptyNote,
                FingerNote(5),
                MultipleFingerNotes(
                    listOf(
                        FingerNote(1, baseNote = true),
                        FingerNote(6),
                    )
                ),
                EmptyNote,
                EmptyNote
            )
        )

        // E
        val MAJOR_TEMPLATE_4 = ChordData(
            markers = listOf(
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 6, true),
                FingerNote(3),
                MultipleFingerNotes(
                    listOf(
                        FingerNote(4),
                        FingerNote(5),
                    )
                ),
                EmptyNote,
                EmptyNote,
                EmptyNote
            )
        )

        // D
        val MAJOR_TEMPLATE_5 = ChordData(
            markers = listOf(
                MuteMarker,
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 4, true),
                EmptyNote,
                MultipleFingerNotes(
                    listOf(
                        FingerNote(1),
                        FingerNote(3),
                    )
                ),
                FingerNote(2),
                EmptyNote,
                EmptyNote
            )
        )

        // MINOR CAGED

        // Cm
        val MINOR_TEMPLATE_1 = ChordData(
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
                MultipleFingerNotes(
                    listOf(
                        FingerNote(2),
                        FingerNote(4),
                    )
                ),
                EmptyNote,
                FingerNote(5, baseNote = true),
                EmptyNote,
                EmptyNote
            )
        )

        // Am
        val MINOR_TEMPLATE_2 = ChordData(
            markers = listOf(
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 5, true),
                FingerNote(2),
                MultipleFingerNotes(
                    listOf(
                        FingerNote(3),
                        FingerNote(4)
                    )
                ),
                EmptyNote,
                EmptyNote,
                EmptyNote
            )
        )

        // Em
        val MINOR_TEMPLATE_3 = ChordData(
            markers = listOf(
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 6, true),
                EmptyNote,
                MultipleFingerNotes(
                    listOf(
                        FingerNote(4),
                        FingerNote(5),
                    )
                ),
                EmptyNote,
                EmptyNote,
                EmptyNote
            )
        )

        // Dm
        val MINOR_TEMPLATE_4 = ChordData(
            markers = listOf(
                MuteMarker,
                MuteMarker,
                BassMarker,
                NormalMarker,
                NormalMarker,
                NormalMarker
            ),
            notes = listOf(
                Barre(1, 4, true),
                FingerNote(1),
                FingerNote(3),
                FingerNote(2),
                EmptyNote,
                EmptyNote
            )
        )
    }
}