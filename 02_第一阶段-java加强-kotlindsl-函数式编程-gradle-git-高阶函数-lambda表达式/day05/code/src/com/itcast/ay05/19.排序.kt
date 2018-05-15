package com.itcast.ay05


/**
 * ClassName:`19.排序`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf("z","b","d")
//    正序排序  b d z
    println(list.sorted())
//    倒序排序
    println(list.sortedDescending())
//    按字段排序

    val list1 = listOf(Person("林青霞",50),Person("张曼玉",30),Person("柳岩",70))
//    list1.sorted()
    val list3 = list1.sortedBy { it.age }
//    println(list3)
    val list4 = list1.sortedByDescending { it.age }
    println(list4)

}
data class Person(var name:String,var age:Int)