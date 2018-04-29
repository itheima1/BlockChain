package com.itcast.day01


/**
 * ClassName:`16.自定义函数`
 * Description:
 */
fun main(args: Array<String>) {
//    sayHello("张三")
    println(getLength("张三"))
}
//无参无返回值
fun sayHello(){//返回值
    println("hello")
}
//有参无返回值
fun sayHello(name:String){
    println("hello "+name)
}
//有参有返回值
fun getLength(name:String):Int{
    return name.length
}
//无参有返回值
fun get():String{
    return "hello"
}