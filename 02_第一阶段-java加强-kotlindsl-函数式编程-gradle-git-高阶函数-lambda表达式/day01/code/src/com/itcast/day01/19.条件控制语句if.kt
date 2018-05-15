package com.itcast.day01


/**
 * ClassName:`19.条件控制语句if`
 * Description:
 */
/**
 * kotlin的if语句有返回值的  java if语句是没有返回值的
 * switch when表达式
 * kotlin一般都有返回值(函数式编程 闭包)
 */
fun main(args: Array<String>) {
    val a = 10
    val b = 20
    //找到a和b中最大值
    val max = max(a, b)//Ctrl+Alt+L
    println(max)
}

//求最大值
//kotlin没有三元运算符
fun max(a: Int, b: Int): Int {
    return if (a > b) {
         a
    } else {
         b
    }
}
//如果只有一行 可以去掉{}
//fun max(a: Int, b: Int): Int {
//    if (a > b)
//        return a
//     else
//        return b
//}
//最终精简
//fun max(a: Int, b: Int): Int {
//    return if (a > b) a else b
//}