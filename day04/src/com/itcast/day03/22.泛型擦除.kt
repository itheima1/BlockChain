package com.itcast.day03.haha


/**
 * ClassName:`22.泛型擦除`
 * 两种类型应该是不一样的
 * 必须要通过反射获取泛型类型
 *
 * 解决泛型擦除方案:
 * 第一步:泛型前加reified关键字
 * 第二步:方法前加上inline关键字
 */
fun main(args: Array<String>) {
//    val box1 = Box<String>("")
//    val box2 = Box<Int>(10)
//
//    //class类型
//    val clz1 = box1.javaClass.name
//    val clz2 = box2.javaClass.name
//
//    println(clz1)
//    println(clz2)
//    String::class.java
    parseType(10)
}
inline fun <reified T>parseType(thing:T){
    //获取传递的thing的class类型
    val name = T::class.java.name
    println(name)
}
