package com.itcast.day01


/**
 * ClassName:`06.智能类型推断和类型转换`
 * Description:
 */
fun main(args: Array<String>) {
    var a: Int = 10

    /*---------------------------- kotlin智能类型推断 ----------------------------*/
    //类型安全的语言:类型一但确定,不再改变了
    //kotlin编译器推断出Int类型
    var b = 10

//    var g
//    var c:Byte = 10
    var e = 10L//long

//    b = 20
//    b = "a"

    var c = "张三"
    var d = 'a'

    /*---------------------------- 类型转换 ----------------------------*/
    // 10   "10"
/*---------------------------- String和int类型转换 ----------------------------*/
    //to转换基本数据类型
    var m = 10
    var s = "10"
    println(s.toInt())
    s.toByte()
    /*---------------------------- int类型和long类型转换 ----------------------------*/
    //不同的数据类型不能相互赋值
    //kotlin数据类型可以通过to方法进行相互转换
    var u = 10
    var v = 10L
    v = u.toLong()//Int赋值给Long类型
    u = v.toInt()//Long赋值给Int类型

    //kotlin                    java                    js
    //最严格的类型检查        只能小的赋值给大的     最宽松
}