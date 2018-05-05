package com.itcat.day02


/**
 * ClassName:`01.for循环和foreach循环`
 * Description:
 */
fun main(args: Array<String>) {
    val str  = "abcd"
    /*---------------------------- for循环 ----------------------------*/
//    for (a in str) {
////        println(a)
////    }
////    for ((index,c) in str.withIndex()) {
////        println("index=$index c=$c")
////    }

    /*---------------------------- foreach ----------------------------*/
//    str.forEach {
//        println(it)
//    }
    str.forEachIndexed { index, c ->
        println("index=$index c=$c")
    }
}