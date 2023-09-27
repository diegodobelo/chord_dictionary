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
import com.diegodobelo.chorddictionary.ui.chord.Chord
import com.diegodobelo.chorddictionary.ui.theme.ChordDictionaryTheme

class MainActivity : ComponentActivity() {
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
                    Chord(ChordsRepository.MAJOR_TEMPLATE_1, "G#", 5)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChordDictionaryTheme {
        Chord(ChordsRepository.MAJOR_TEMPLATE_1, "A", 6)
    }
}