package com.itcat.day02

import java.io.BufferedReader
import java.io.File
import java.io.FileReader


/**
 * ClassName:`20.异常`
 * Description:
 * java异常种类:编译时异常  运行时异常
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 0

    //异常处理
    try {
        a / b
    } catch (e: ArithmeticException) {
        println("捕获到异常")
    }finally {
        println("最终要执行的代码")
    }


    /*---------------------------- koltin编译时异常 ----------------------------*/
    //kotlin不检查编译时异常
    //kotlin认为大部分的编译时异常都是没有必要的
    val file = File("a.txt")
    val bfr = BufferedReader(FileReader(file))
//    ArithmeticException
}