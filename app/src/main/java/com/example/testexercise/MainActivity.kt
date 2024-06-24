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
        // Start task2 synchron, blocking task1 if not delayed
        GlobalScope.launch {
            text2 = task2()
        }
        // Start task1 after task2
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
    return "Hello\n${Thread.currentThread().name}\n"

}

suspend fun task2(): String {
    withContext(Dispatchers.IO) {
        delay(3000L) // 3 Sekunden warten
    }
    return "World!!!\n${Thread.currentThread().name}\n"
}