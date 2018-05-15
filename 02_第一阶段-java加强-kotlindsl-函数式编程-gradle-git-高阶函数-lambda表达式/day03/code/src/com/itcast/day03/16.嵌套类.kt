package com.itcast.day03



/**
 * ClassName:`16.嵌套类`
 * 嵌套类是属于静态类 和外部类没有任何关系
 */
fun main(args: Array<String>) {
    //创建嵌套类对象
    val inClass = OutClass.InnnerClass()
}
class OutClass{
    var name = "张三"
    class InnnerClass{
        fun sayHello(){
//            println("你好$name")
        }
    }
}