package com.itcast.ay05


/**
 * ClassName:`06.map遍历`
 * Description:
 */
fun main(args: Array<String>) {
    val map = mapOf("中国" to "China","英国" to "England","美国" to "USA")
    //遍历map集合
    /*---------------------------- 遍历所有的key ----------------------------*/
    val keySet = map.keys
//    keySet.forEach { println(it) }
    /*---------------------------- 遍历所有的value ----------------------------*/
    val values = map.values
//    values.forEach { println(it) }
    /*---------------------------- key和value ----------------------------*/
//    val entrys = map.entries
    map.forEach { t, u ->
        println("key=$t value=$u")
    }
    for ((key,value) in map) {
        println("key=$key value=$value")
    }
}