package com.itcast.day01


/**
 * ClassName:`11.字符串切割`
 * Description:
 */
fun main(args: Array<String>) {
    val str = "张三.李四-王五"
    //多条件切割
    val result = str.split(".","-")
    println(result)
}