package com.itcast.day01


/**
 * ClassName:`07.可变变量和不可变变量`
 * Description:
 */
/**
 * 项目开发中尽量使用val  实在不能使用val再使用var
 */
//可以通过反射修改里面的值
const val b:Int = 20
fun main(args: Array<String>) {
    //可变变量
    var a:Int = 10
    a = 20
    val d = 20
    //不可变变量

//    b = 30
}