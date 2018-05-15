package com.itcast.ay05


/**
 * ClassName:`12.有参的lambda表达式`
 * Description:
 */
fun main(args: Array<String>) {
    //嵌套有参的lambda表达式  实现a+b的和
    val result = {a:Int,b:Int->
        a+b
    }.invoke(10,20)
    println(result)
    //10+20的和
//    fun add(a:Int,b:Int):Int{
//        return a+b
//    }
}