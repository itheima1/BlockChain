package com.itcast.day04.属性委托

import kotlin.reflect.KProperty


/**
 * ClassName:`05.属性委托`
 * Description:
 */
fun main(args: Array<String>) {
    val bigHeadSon = BigHeadSon()
    //爷爷奶奶给了100块钱
    bigHeadSon.压岁钱 = 150
    //取压岁钱
    println(bigHeadSon.压岁钱)
}
class BigHeadSon{
    //存钱罐
    var 压岁钱:Int by Mother()
}
class Mother{
    //儿子要压岁钱
    operator fun getValue(bigHeadSon: BigHeadSon, property: KProperty<*>): Int {
        return 儿子的压岁钱
    }
    //儿子存压岁钱  i设置的值  100
    operator fun setValue(bigHeadSon: BigHeadSon, property: KProperty<*>, i: Int) {
        儿子的压岁钱 += 50

        自己的小金库 += i-50
    }

    var 儿子的压岁钱 = 0

    var 自己的小金库 = 0
}