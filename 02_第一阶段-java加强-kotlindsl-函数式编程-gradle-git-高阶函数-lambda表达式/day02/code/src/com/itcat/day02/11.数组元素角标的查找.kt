package com.itcat.day02


/**
 * ClassName:`11.数组元素角标的查找`
 * Description:
 */
fun main(args: Array<String>) {
    val array = arrayOf("张三","李四","张四","王五","张三","赵六")
    //查找第一个”张三”角标
    //返回第一对应的元素角标  如果没有找到返回-1
    val index1 = array.indexOf("张三")
    println(index1)
    //查找最后一个”张三”角标
    //找不到 返回-1
    val index2 = array.lastIndexOf("张三")
    println(index2)

    /*---------------------------- 高阶函数实现 ----------------------------*/
    //查找第一个姓”张”的人的角标
//    val index3 = array.indexOfFirst {
//        it.startsWith("张")
//    }
//    println(index3)
    //查找最后一个姓”张”的人的角标
    val index4 = array.indexOfLast { it.startsWith("张") }
    println(index4)

    val index5 = array.indexOfFirst { it=="张三" }
    println(index5)
}