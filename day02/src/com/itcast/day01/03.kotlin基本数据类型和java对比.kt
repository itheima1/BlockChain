package com.itcast.day01


/**
 * ClassName:`03.kotlin基本数据类型和java对比`
 * Description:
 */
fun main(args: Array<String>) {
    var a:Int = 10
    //基本数据类型
    var b:Int = a+10
    //调用hashcode
    val hashCode:Int = a.hashCode()

    println(hashCode)
}