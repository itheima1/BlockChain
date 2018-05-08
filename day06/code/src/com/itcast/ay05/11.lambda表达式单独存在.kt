package com.itcast.ay05


/**
 * ClassName:`11.lambda表达式单独纯在`
 * Description:
 */
//顶层函数  嵌套函数
fun main(args: Array<String>) {
    //嵌套匿名函数
    //和sayHello一样功能的函数
//    {
//        println("hello")
//    }()
    {
        println("hello")
    }.invoke()
    //不能通过名称来调用

    //有名函数
//    fun sayHello(){
//        println("hello")
//    }
//    sayHello()

}