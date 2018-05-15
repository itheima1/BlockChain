package com.itcast.ay05.函数回调


/**
 * ClassName:Mother
 * Description:mother
 */
fun main(args: Array<String>) {
    //创建超市对象
    val superMarket = SuperMarket()
    //买酱油
    //lambda表达式
//    superMarket.buySoy{
//        println("取回了${it.name}酱油")
//        println("妈妈开始做菜")
//    }

    superMarket.buySoy(null)

//    println("买到${soy.name}牌酱油")

    println("做甜点")
}