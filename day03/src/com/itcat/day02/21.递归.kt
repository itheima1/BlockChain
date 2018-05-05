package com.itcat.day02


/**
 * ClassName:`21.递归`
 * Description:
 */
fun main(args: Array<String>) {
    //阶乘  5阶乘  5*4*3*2*1  5*4的阶乘 1的阶乘 1 n的阶乘   n*(n-1)的阶乘
    //求n的阶乘
//    val result = fact(5)
//    println(result)

    val result = fibonacci(8)
    println(result)
}

/**
 * 求n的阶乘
 */
fun fact(n:Int):Int{
        if(n==1){
            return 1
        }else{
            return n* fact(n-1)
        }
}

//斐波那契数列  1 1 2 3 5 8 13 21 34 55
//第四斐波那契数列=第三个斐波那契数列+第二个斐波那契数列
//第n个   第n-2个  + 第n-1个
/**
 * 求第n个斐波那契数列
 */
fun fibonacci(n:Int):Int{
    if(n==1||n==2){
        return 1
    }else{
        return fibonacci(n-2)+ fibonacci(n-1)
    }
}