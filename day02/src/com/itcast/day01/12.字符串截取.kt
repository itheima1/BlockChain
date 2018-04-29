package com.itcast.day01


/**
 * ClassName:`12.字符串截取`
 * Description:
 */
fun main(args: Array<String>) {
    val path = "/Users/yole/kotlin-book/chapter.adoc"
    //获取前6个字符
//    println(path.substring(0, 6))
    println(path.substring(0..5))//0到5
    //把第一个r之前的字符截取
    println(path.substringBefore("r"))
    //把最后一个r之前的字符截取
    println(path.substringBeforeLast("r"))
    //把第一个r之后的字符截取
    println(path.substringAfter("r"))
    //把最后一个r之后的字符截取
    println(path.substringAfterLast("r"))
}