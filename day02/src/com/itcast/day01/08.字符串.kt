package com.itcast.day01


/**
 * ClassName:`08.字符串`
 * Description:
 */
/**
 * 广东省
 * 深圳市
 * 宝安区
 */
//模板性
fun main(args: Array<String>) {
    /*---------------------------- 普通字符串 ----------------------------*/
//    val place1 = "广东省深圳市宝安区"
////    println(place1)
//    //换行
//    val place2 = "广东省\n深圳市\n宝安区"
////    println(place2)
//    val place3 = "广东省" +
//            "深圳市" +
//            "宝安区"
////    println(place3)
//    //怎样写怎样输出?
    /*---------------------------- 原样输出字符串 ----------------------------*/
    val place4 = """
        广东省
        深圳市
        宝安区
    """.trimIndent()

    println(place4)
}