package com.diegodobelo.chorddictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diegodobelo.chorddictionary.repository.ChordsRepository
import com.diegodobelo.chorddictionary.templates.MajorTemplates
import com.diegodobelo.chorddictionary.ui.chord.Chord
import com.diegodobelo.chorddictionary.ui.theme.ChordDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var chordsRepository: ChordsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChordDictionaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(8.dp)
                ) {
                    // TODO: check why >A doesn't work
                    // TODO: also get visible frets from repository
                    val chorData = chordsRepository.getMajorChordsForTune(
                        "F",
                        MajorTemplates.MAJOR_TEMPLATE_1
                    )
                    // TODO: make visibleFrets base 1, or change the name to lastVisibleFretIndex,
                    // TODO: also, get it from repository
                    Chord(
                        chordData = chorData,
                        visibleFrets = 5
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChordDictionaryTheme {
        Chord(MajorTemplates.MAJOR_TEMPLATE_1,6)
    }
}