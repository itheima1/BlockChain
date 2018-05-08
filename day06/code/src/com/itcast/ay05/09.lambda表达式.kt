package com.itcast.ay05.lambda


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
    sum = cacl(a,b,{m:Int,n:Int->
        m+n  //return m+n
    })//第三个参数应该是函数的引用
    result = cacl(a,b,{m,n->
        m-n
    })

    println(sum)
    println(result)
}

fun cacl(m:Int,n:Int,block:(Int,Int)->Int):Int{
    val result = block(m,n)
    return result
}
