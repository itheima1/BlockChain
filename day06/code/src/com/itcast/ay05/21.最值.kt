package com.itcast.ay05


/**
 * ClassName:`21.最值`
 * Description:
 */
fun main(args: Array<String>) {
    val  list = listOf("z","g","r")
//    最大值
    println(list.max())
//    最小值
    println(list.min())

    val list1 = listOf(Person("林青霞",50),Person("张曼玉",30),Person("柳岩",70))
//    对象最大值
    list1.maxBy { it.age }
//    对象最小值
    list1.minBy { it.age }

}