package com.itcat.day02.haha


/**
 * ClassName:`19.可变桉树`
 * Description:
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    var c = 30
    //a+b+c
    val result = add(a, b,c,10,20,30,40,50)
    println(result)
}

/**
 * 只要是Int数据类型  无论你传递多少个我都能求他们的和  可变参数
 */
fun add(vararg a:Int):Int{//数组
   //a是什么类型?
   //智能类型推断
//    val newa = a
    var result = 0//和
    a.forEach {
        result += it
    }
    return result
}