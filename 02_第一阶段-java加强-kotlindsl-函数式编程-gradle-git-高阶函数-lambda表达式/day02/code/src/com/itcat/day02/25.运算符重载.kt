package com.itcat.day02.haha


/**
 * ClassName:`25.运算符重载`
 * kotlin里面每一个运算符对应的都是一个方法 运算符相当于是方法的简写
 * 第一步:找到对应的函数
 * 第二步:函数前加上operator
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    a.plus(b)

    val girl1 = Girl()
    val girl2 = Girl()
    girl2.name = "王五"
    val newGirl = girl1 + girl2
    println(newGirl)

//    println(newGirl.age)
}
class Girl{
    //定义对应的运算符函数
//    operator fun plus(girl:Girl):Girl{
//        return this
//    }
//    operator fun plus(age:Int):Girl{
//        this.age += age
//        return this
//    }
    operator fun plus(girl:Girl):Int{
        return this.age+girl.age
    }
    var name:String = "张三"
    var age:Int = 20
}