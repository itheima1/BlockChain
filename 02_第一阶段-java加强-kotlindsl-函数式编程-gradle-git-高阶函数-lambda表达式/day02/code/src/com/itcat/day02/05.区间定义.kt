package com.itcat.day02


/**
 * ClassName:`05.区间定义`
 * Description:100到1 反向区间
 */
fun main(args: Array<String>) {
    /*---------------------------- 定义1到100 ----------------------------*/
    val range1 = 1..100
    1 until 100 //[1,100)
    val range2 = IntRange(1,100)
    val range3 = 1.rangeTo(100)
    /*---------------------------- 长整形区间 ----------------------------*/
    val range4 = 1L..100L
    val range5 = LongRange(1L,100L)
    val range6 = 1L.rangeTo(100L)
    /*---------------------------- 字符区间 ----------------------------*/
    val range7 = 'a'..'z'
    val range8 = CharRange('a','z')
    val range9 = 'a'.rangeTo('z')

//    val range10 = '张'..'李'
}