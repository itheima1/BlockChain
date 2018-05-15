package com.itcast.ay05


/**
 * ClassName:`13.保存lambda表达式`
 * Description:
 */
fun main(args: Array<String>) {
    //foreach it
    //保存lambda表达式
    val block:(()->Unit)? = null //可空的函数变量类型
//            {
//                println("hello")
//            }
    //调用lambda表达式
//    block()
    block?.invoke()

    test()
    test()
}
val test = {
    println("hello")
}