package com.itcat.day02


/**
 * ClassName:`07.反向区间和区间的反转`
 * Description:
 */
fun main(args: Array<String>) {
    /*---------------------------- 反向区间 ----------------------------*/
    //定义100到1的区间
    val range = 100 downTo 1
//    range.forEach {
//        println(it)
//    }
    /*---------------------------- 区间反转 ----------------------------*/
    val range1 = 1..100
    val range2 = range1.reversed()
//    range2.forEach {
//        println(it)
//    }
    for (i in range2 step 5) {
        println(i)
    }
}