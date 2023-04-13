package com.bignerdranch.android.coroutineflow.lesson.lesson2

fun main() {

    val nambers = listOf<Int>(2,43,42,53,23,45,4,24,65,3)

    nambers.filter { it.isPrime() }
        .filter { it >1 }
        .map { "Map - $it" }
        .forEach { println(it) }


}
fun Int.isPrime():Boolean{
    if (this <= 1 ){return false}
    for (i in 2..this-1){
        if (this % i ==0 ){
            return false
        }
    }
return true
}