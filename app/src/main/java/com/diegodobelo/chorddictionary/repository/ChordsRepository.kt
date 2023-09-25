package com.diegodobelo.chorddictionary.repository

import com.diegodobelo.chorddictionary.models.Barre
import com.diegodobelo.chorddictionary.models.BassMarker
import com.diegodobelo.chorddictionary.models.ChordData
import com.diegodobelo.chorddictionary.models.EmptyNote
import com.diegodobelo.chorddictionary.models.NoteOnString
import com.diegodobelo.chorddictionary.models.NotesOnFret
import com.diegodobelo.chorddictionary.models.MuteMarker
import com.diegodobelo.chorddictionary.models.NormalMarker

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
                NoteOnString(2),
                NoteOnString(4),
                NoteOnString(5, baseNote = true),
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
                NotesOnFret(
                    listOf(
                        NoteOnString(2),
                        NoteOnString(3),
                        NoteOnString(4)
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
                NoteOnString(5),
                NotesOnFret(
                    listOf(
                        NoteOnString(1, baseNote = true),
                        NoteOnString(6),
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
                NoteOnString(3),
                NotesOnFret(
                    listOf(
                        NoteOnString(4),
                        NoteOnString(5),
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
                NotesOnFret(
                    listOf(
                        NoteOnString(1),
                        NoteOnString(3),
                    )
                ),
                NoteOnString(2),
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
                NotesOnFret(
                    listOf(
                        NoteOnString(2),
                        NoteOnString(4),
                    )
                ),
                EmptyNote,
                NoteOnString(5, baseNote = true),
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
                NoteOnString(2),
                NotesOnFret(
                    listOf(
                        NoteOnString(3),
                        NoteOnString(4)
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
                NotesOnFret(
                    listOf(
                        NoteOnString(4),
                        NoteOnString(5),
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
                NoteOnString(1),
                NoteOnString(3),
                NoteOnString(2),
                EmptyNote,
                EmptyNote
            )
        )
    }
}