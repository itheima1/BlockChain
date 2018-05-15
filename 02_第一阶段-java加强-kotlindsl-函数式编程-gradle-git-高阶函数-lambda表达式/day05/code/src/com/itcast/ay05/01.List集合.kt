package com.itcast.ay05

import java.util.*


/**
 * ClassName:`01.List集合`
 * Description:
 */
fun main(args: Array<String>) {
    /*---------------------------- listof ----------------------------*/
    //林青霞  高圆圆 范冰冰
    val list1 = listOf("林青霞","高圆圆","范冰冰")
    //不能添加元素
    //不能修改元素
    //只读List集合

//    list1[0] = "柳岩"

//    list1.forEach {
//        println(it)
//    }

    /*---------------------------- mutableListOf ----------------------------*/
    //返回的也是ArrayList集合
    val list2 = mutableListOf("林青霞","高圆圆","范冰冰")
    //修改第一个元素
    list2.set(0,"柳岩")
    //添加元素
    list2.add(1,"刘诗诗")
    println(list2)

    /*---------------------------- java的集合 ----------------------------*/
    val list3 = arrayListOf("林青霞","","")
    val list4 = ArrayList<String>()

    val vector = Vector<String>()

}