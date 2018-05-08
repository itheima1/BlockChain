package com.itcast.day04


/**
 * ClassName:`13.枚举加强`
 * Description:枚举里面可以定义构造函数
 */
fun main(args: Array<String>) {
    COLOR.RED.r
    COLOR.RED.g
    COLOR.RED.b
//    COLOR()
}
//枚举三元素
// 红  r 255 g 0 b 0
// 蓝  r 0 g 0 b 255
// 绿  r 0 g 255 b 0
enum class COLOR(var r:Int,var g:Int,var b:Int){
    RED(255,0,0),BLUE(0,0,255),GREEN(0,255,0)
}