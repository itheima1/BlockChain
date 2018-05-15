package com.itcast.ay05


/**
 * ClassName:`14.lambda表达式`
 * Description:lambda表达式使用的时候如果只有一个参数可以省略参数名,默认是通过it来实现的
 */
fun main(args: Array<String>) {

    var a = 10
    //调用haha 函数
    val result = haha(a, {
        it + 10
    })
    println(result)
}

//高阶函数
fun haha(m: Int, block: (Int) -> Int): Int {
    val result = block(m)
    return result
}
//
//val plambda =
//        { m: Int ->
//            m + 10
//        }
//fun padd(a:Int):Int{
//    return a+10
//}