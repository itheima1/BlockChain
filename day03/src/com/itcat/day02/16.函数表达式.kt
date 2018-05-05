package com.itcat.day02


/**
 * ClassName:`16.函数表达式`
 * Description:
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    //a+b
    println(add(a, b))
}
//函数体只有一行
//fun add(a:Int,b:Int):Int{
//    return a+b
//}
//函数体只有一行代码  可以省略{} 省略return 用=连接
//fun add(a:Int,b:Int):Int =  a+b
//返回值类型也不用写
fun add(a:Int,b:Int) =  a+b

fun sayHello(){
    println("hello")
    println("hello world")
}
