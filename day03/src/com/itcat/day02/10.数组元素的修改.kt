package com.itcat.day02


/**
 * ClassName:`10.数组元素的修改`
 * Description:
 */
fun main(args: Array<String>) {
    val array = arrayOf(1,2,3,4,5)
    //把3修改成9
    array[2] = 6

    array.set(2,9)

    array.forEach {
        println(it)
    }
}