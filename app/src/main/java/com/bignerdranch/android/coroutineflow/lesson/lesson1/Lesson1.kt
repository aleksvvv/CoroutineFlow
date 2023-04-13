package com.bignerdranch.android.coroutineflow.lesson.lesson1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlin.concurrent.thread

suspend fun main() {

    val numbers = listOf<Int>(2, 43, 42, 53, 23, 45, 4, 24, 65, 3).asFlow()

    numbers.filter { it.isPrime() }
        .filter { it > 1 }
        .map {
            println("Map")
            "Map - $it"
        }
        .collect { println(it) }


}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) {
        return false
    }
    for (i in 2..this - 1) {
        delay(50)
        if (this % i == 0) {
            return false
        }
    }
    return true
}