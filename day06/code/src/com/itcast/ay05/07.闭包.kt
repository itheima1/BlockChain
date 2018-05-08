package com.itcast.ay05


/**
 * ClassName:`07.闭包`
 * Description:
 */
fun main(args: Array<String>) {
//    test()
//    test()
//    test()
    val result = test()
    result()
    result()
    result()
}
//闭包 lambda表达式 函数式编程 函数可以作为方法的返回值 方法可以作为函数的参数
fun test():()->Unit{
    var a  =10
    return {
        println(a)
        a++
    }
}

//函数不保存状态  闭包可以让函数携带状态
//fun test(){
//    var a  = 10
//    println(a)
//    a++
//}