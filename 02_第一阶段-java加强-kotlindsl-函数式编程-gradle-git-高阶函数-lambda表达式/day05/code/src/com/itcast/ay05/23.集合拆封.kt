package com.itcast.ay05


/**
 * ClassName:`23.集合拆封`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf("张三","李四","王五","赵六","张四","李五","张三","李六")
    //姓张的一部分,另外的一部分
    val pair = list.partition {
        it.startsWith("张")
    }
    println(pair.first)
    println(pair.second)

}