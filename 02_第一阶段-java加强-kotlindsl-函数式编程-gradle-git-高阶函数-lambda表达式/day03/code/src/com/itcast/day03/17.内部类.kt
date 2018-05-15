package com.itcast.day03.haha



/**
 * ClassName:`16.嵌套类`
 * 内部类和java的内部类是一样的 需要依赖于外部类对象的环境
 */
fun main(args: Array<String>) {
    //创建内部类对象
    val inClass  = OutClass().InnnerClass()
}
class OutClass{
    var name = "张三"
    inner class InnnerClass{
        fun sayHello(){
            println("你好$name")
        }
    }
}