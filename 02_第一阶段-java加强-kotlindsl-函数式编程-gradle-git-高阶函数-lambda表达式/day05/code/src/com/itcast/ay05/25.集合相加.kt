package com.itcast.ay05


/**
 * ClassName:`25.集合相加`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf(Person("林青霞",50),Person("张曼玉",30),Person("柳岩",70))
    //求出所有人的年龄之和
    println(list.sumBy {
        it.age
    })
//    list.sumByDouble {
//        it.age
//    }
}