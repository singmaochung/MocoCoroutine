package com.example.testexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testexercise.ui.theme.TestExerciseTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestExerciseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var text1 by remember { mutableStateOf("Starting task1...\n") }
    var text2 by remember { mutableStateOf("Starting task2...\n") }

    LaunchedEffect(Unit) {
        TODO("Mach aus task2 eine Coroutine")
        // Frage an dich: Was würde passieren wenn es keine ist? Was ist der Vorteil?
        text2 = task2()
        text1 = task1()

    }

    Column(modifier.padding(16.dp)) {
        Text(
            text = text1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = text2
        )
    }
}

fun task1(): String {
    TODO("Gebe 'Hello' zurück und den namen des Threads auf dem task1 ausgeführt wird")
}

suspend fun task2(): String {
    TODO("Gebe den Dispatcher zurück")
    TODO("Gebe 'World' und den namen des Threads zurück")
    TODO("Passe die funktion so an das zuerst task1 ausgeführt wird")
}