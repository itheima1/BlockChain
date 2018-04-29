package com.itcast.day01


/**
 * ClassName:`09.字符串删除空格`
 * Description:
 */
fun main(args: Array<String>) {
    /*---------------------------- 普通字符串删除空格 ----------------------------*/
    val str = "  张三   "
    val newStr = str.trim()
//    println(newStr)
    /*---------------------------- 原样输出字符串 ----------------------------*/
    val str2 = """
        /张三
        /李四
        /王五
    """.trimMargin("/")
    println(str2)
}