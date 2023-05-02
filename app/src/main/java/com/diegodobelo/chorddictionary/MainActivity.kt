package com.diegodobelo.chorddictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diegodobelo.chorddictionary.ui.chord.Dot
import com.diegodobelo.chorddictionary.ui.chord.GuitarArm
import com.diegodobelo.chorddictionary.ui.theme.ChordDictionaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChordDictionaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuitarArm()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    GuitarArm()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChordDictionaryTheme {
        Greeting("Android")
    }
}