package com.itcast.day01


/**
 * ClassName:`14.空值处理`
 * Description:
 */
/**
 * val str:String 非空类型 不能赋值为null
 * val str: String? 可空类型 可以赋值为null
 *
 *  空安全调用符:?.
 */
/**
 * 空值处理的运算符
 * 可空类型  Int?
 * 关闭空检查  a!!
 * 空安全调用符 a?.toInt
 * Elvis操作符  ?:
 */
//swift
fun main(args: Array<String>) {
    //10   "10" 可空类型
    val str: String? = null
    //转换为Int类型
    //告诉编译器  不要检查了  我一定不为空  还是可能为空  不建议使用
//    str!!.toInt()
    //空安全调用符 返回的值其实就是可空类型
//    str?.toInt()

//    Int?
//    if(str!=null){
//        return str.toInt()
//    }else{
//        return -1;
//    }
//    Int?
    val b:Int = str?.toInt()?:-1
//    if(str!=null){
//        return str.toInt()
//    }else{
//        return -1
//    }
    println(b)

}