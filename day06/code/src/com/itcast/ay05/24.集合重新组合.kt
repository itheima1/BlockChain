package com.itcast.ay05


/**
 * ClassName:`24.集合重新组合`
 * Description:
 */
fun main(args: Array<String>) {
    val list1 = listOf(Person("林青霞",50),Person("张曼玉",30),Person("柳岩",70))
    //将Person里面每一个name获取
    val list2 = list1.map {
        it.name.substring(0,1)//姓氏
    }
    println(list2)
}