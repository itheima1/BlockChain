package com.itcast.ay05


/**
 * ClassName:`15.lambda表达式返回值`
 * Description:lambda表达式返回不用return  返回值是最后一行的返回值
 */
fun main(args: Array<String>) {
    //test代表的是lambda表达式的返回值
    val test =
            {
                10
                "hello"
                println("hello")
                'a'
            }()
}