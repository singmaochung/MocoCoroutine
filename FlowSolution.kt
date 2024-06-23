package com.example.flowimplementation


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

//Erstelle einen Flow, der die Zahlen 1-5 mittels Schleife emittiert.
// Die Werte sollen collected und ausgegeben werden

fun main() = runBlocking {20
    val numberFlow: Flow<Int> = flow {
        for (i in 1..5) {
            emit(i)
        }
    }


    numberFlow.collect { value ->
        println(value)
    }
}