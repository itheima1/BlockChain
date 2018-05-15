package com.itcast.day03.h


/**
 * ClassName:`08.init和次构执行顺序`
 * Description:
 */
fun main(args: Array<String>) {
    val person = Person("张三",20,"12333")

}
class Person(var name:String,var age:Int){
    init {
        println("执行了初始化")
    }
    constructor(name:String,age:Int,phone:String):this(name, age){
        println("执行了次构函数")
    }
}