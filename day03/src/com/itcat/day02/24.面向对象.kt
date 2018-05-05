package com.itcat.day02


/**
 * ClassName:`24.面向对象`
 * Description:
 */
fun main(args: Array<String>) {
    val girl = Girl()
    println(girl.name)
    println(girl.age)
    girl.greeting()

    val rect = Rect()
    println(rect.long)
    println(rect.width)
}
//矩形
class Rect{
    //静态属性
    var long:Int = 100
    var width:Int = 100
}
//妹子
class Girl{
    //静态属性
    var name:String = "李四"
    var age:Int = 20
    //动态行为
    fun greeting(){
        println("hello  你好")
    }
}