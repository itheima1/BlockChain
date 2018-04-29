package com.itcast.day01


/**
 * ClassName:`13.二元元组`
 * Description:
 */
fun main(args: Array<String>) {
    //定义二元元组 姓名  年纪
//    val pair = Pair<String,Int>("张三",20)
    val pair = "张三" to 20
//    println(pair.first)
//    println(pair.second)

    //三元元组
    val triple = Triple<String,Int,String>("李四",20,"15456678")
    println(triple.first)
    println(triple.second)
    println(triple.third)

//    1/0
}
