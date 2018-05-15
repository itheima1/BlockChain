package com.itcast.day04.惰性加载

import com.itcast.day04.延迟加载.name1


/**
 * ClassName:`06.惰性加载`
 * 字段:val  不可变
 * by lazy放到成员变量中 可以单独存在
 * by lazy返回值就是最后一行
 * by lazy线程安全 (同步)
 */
val name1: String  by lazy {
    println("初始化了")
    "张三"
}
val age = 20

fun main(args: Array<String>) {
    println(name1)
    println(name1)
}
//class Person{
//    //用的时候再赋值
//    val name1:String  by lazy { "张三" }
//    val name2:String = "张三"
//    val name3:String = "张三"
//    val name4:String = "张三"
//
//}