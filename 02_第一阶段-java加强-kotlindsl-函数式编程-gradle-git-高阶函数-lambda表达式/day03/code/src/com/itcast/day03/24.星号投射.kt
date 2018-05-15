package com.itcast.day03.hahha


/**
 * *可以传递任何类型  相当于java的 ?
 */
fun main(args: Array<String>) {
    val list = ArrayList<Int>()
    setFruitList(list)
}
//ArrayList里面可以接收任何类型 怎么办?
fun setFruitList(list:ArrayList<*>){//接收只能是Fruit Apple是Fruit子类
    println(list.size)
}