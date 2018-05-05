package com.itcat.day02.com


/**
 * ClassName:`16.函数表达式`
 * Description:
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    //a+b
//    println(add(a, b))
    /*---------------------------- 函数引用:: ----------------------------*/
    //对象变量
    //函数变量
//    val padd= ::add//::获取add函数的引用
    //类似C语言函数指针
    //通过padd调用函数
//    println(padd(10, 20))
//    println(padd?.invoke(20, 30))//可以处理函数变量为空的情况下调用
    /*---------------------------- 函数变量 ----------------------------*/
    val padd:(Int,Int)->Int = {a,b->a+b}//匿名函数
    println(padd(10, 20))
}
//函数体只有一行
//顶层函数
//fun add(a:Int,b:Int):Int{
//    return a+b
//}
