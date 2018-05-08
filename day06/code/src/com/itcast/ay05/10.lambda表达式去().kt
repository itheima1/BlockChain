package com.itcast.ay05.lambda.去括号


/**
 * ClassName:`08.高阶函数`
 * Description:
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    var sum = 0//a+b
    var result = 0//a-b

    //只能我用这个工具  不能让其他人用
    //函数的参数定义出来之后 可以自动推断出类型  返回值不需要写 推断出当前的返回值类型
    //匿名函数 lambda表达式
    //调用的时候最后一个参数传递的是匿名函数lambda表达式
    //如果最后一个参数是lamba表达式时  可以把()前移
    sum = cacl(a, b) { m, n ->
        m + n
    }//第三个参数应该是函数的引用
    result = cacl(a, b) { m, n ->
        m - n
    }

    println(sum)
    println(result)
}


//高阶函数 第三个参数是函数参数  最后一个参数是函数参数
fun cacl(a: Int, b: Int, block: (Int, Int) -> Int): Int {
    val result = block(a, b)
    return result
}
