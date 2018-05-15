package com.itcast.ay05


/**
 * ClassName:`08.高阶函数`
 * Description:
 */
fun main(args: Array<String>) {
    var a = 10
    var b = 20
    var sum = 0//a+b
    var result = 0//a-b
//    sum = add(a, b)//自己使用工具求a+b的和
//    result = sub(a, b)//自己使用工具求a-b的差
//    println(sum)
//    println(result)

    //调用cacl函数传递需要的工具 返回对应的值
    sum = cacl(a,b,::add)//第三个参数应该是函数的引用
    result = cacl(a,b,::sub)
    println(sum)
    println(result)

}
//找另外一个人  给它什么工具它就会根据当前这个工具求出对应的值
/**
 * a:传递的第一个数据
 * b:传递的第二个数据
 * block:传递的工具  add  sub
 * 返回值:使用工具求出的值
 */
//第三个参数是函数类型 说明kotlin里面的函数可以传递函数参数  如果函数里面传递函数参数的话 就称为高阶函数
fun cacl(a:Int,b:Int,block:(Int,Int)->Int):Int{
    //使用工具
    val result = block(a,b)
//    val result = block.invoke(a,b)

    return result
}


//求两个数的和  工具 普通函数
fun add(a: Int, b: Int): Int {
    return a + b
}
//求两个数的差  工具
fun sub(a: Int, b: Int): Int {
    return a - b
}