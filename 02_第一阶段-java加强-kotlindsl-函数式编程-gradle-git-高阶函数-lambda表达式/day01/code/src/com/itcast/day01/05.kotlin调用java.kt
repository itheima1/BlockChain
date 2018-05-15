package com.itcast.day01

import java.math.BigDecimal


/**
 * ClassName:`05.kotlin调用java`
 * Description:
 */
fun main(args: Array<String>) {
    //1.123456789123456789
    //存钱
    var money:Double = 1.123456789123456789
    //取钱
    println(money)


    //银行存钱
    var bigDe:BigDecimal = BigDecimal("1.123456789123456789")//省略new关键字
    //取钱
    println(bigDe)

}