package com.itcast.day01


/**
 * ClassName:`10.字符串的比较`
 * Description:
 */
fun main(args: Array<String>) {
    val str1 = "abc"
    val str2 = String(charArrayOf('a','b','c'))
    //equals  比较值  true
    println(str1.equals(str2))
    //== 比较的也是值
    println(str1 == str2)

    //=== 比较地址值 false
    println(str1 === str2)
}