package com.itcast.ay05


/**
 * ClassName:`22.去重复`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf("张三","李四","王五","赵六","张四","李五","张三","李六")
//    把重复的张三去掉
    val set = list.toSet()
    println(set)
    //list集合
    println(list.distinct())
    list.distinctBy {  }
//    把重复的同姓的去掉

    println(list.distinctBy {
        //张  李  往  找
        it.substring(0, 1)
    })
}