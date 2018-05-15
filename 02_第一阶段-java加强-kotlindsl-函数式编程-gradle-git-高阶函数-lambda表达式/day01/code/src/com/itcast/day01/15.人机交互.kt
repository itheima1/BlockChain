package com.itcast.day01


/**
 * ClassName:`15.人机交互`
 * Description:
 */
fun main(args: Array<String>) {
    //输出数据
    val a = 10
//    println(a)

    //m+n
    var m:Int
    var n:Int
    //从控制台输入m和n
    m = readLine()?.toInt()?:-1
    n = readLine()?.toInt()?:-1

    println("m+n=" + (m + n))
}