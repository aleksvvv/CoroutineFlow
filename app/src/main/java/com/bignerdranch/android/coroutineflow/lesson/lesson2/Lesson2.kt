package com.bignerdranch.android.coroutineflow.lesson.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.concurrent.thread

suspend fun main() {

    getFlow().filter { it.isPrime() }
        .filter { it > 1 }
        .map {
            println("Map")
            "Map - $it"
        }
        .collect { println(it) }


}

fun getFlowOf(): Flow<Int> {
    return flowOf(2, 43, 42, 53, 23, 45, 4, 24, 65, 3)
}
fun getFlow(): Flow<Int> {
    val numbers = getFlowOf()
    return flow {
//        numbers.collect(){
//            println(it)
//            emit(it)
//        }
        emitAll(numbers)
    }
}

//fun getFlow(): Flow<Int> {
//    val numbers = listOf<Int>(2, 43, 42, 53, 23, 45, 4, 24, 65, 3)
//    return flow {
//        for (i in numbers) {
//            emit(i)
//            println(i)
//        }
//    }
//}

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
