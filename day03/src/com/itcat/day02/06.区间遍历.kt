package com.itcat.day02


/**
 * ClassName:`06.区间遍历`
 * Description:
 */
fun main(args: Array<String>) {
    val range = 1..100
    /*---------------------------- for ----------------------------*/
//    for (i in range) {
//        println(i)
//    }
//    for ((index,i) in range.withIndex()) {
//        println("index=$index i=$i")
//    }
    /*---------------------------- foreach ----------------------------*/
//    range.forEach {
//        println(it)
//    }
    range.forEachIndexed { index, i ->
        println("index=$index i=$i")
    }
}