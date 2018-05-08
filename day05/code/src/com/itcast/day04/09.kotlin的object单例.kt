package com.itcast.day04


/**
 * ClassName:`09.kotlin的object单例`
 * Description:object单例  所有的字段都是static静态  方法不是
 * object试用条件:字段不多的时候
 * java可以控制字段是静态还是非静态 static
 * kotlin没有static关键字
 * 怎么控制有些字段是static的,有些字段是非static的?
 */
fun main(args: Array<String>) {
//    val utils1 = Utils()
//    val utils2 = Utils()
    //单例调用方法和属性的方式
    Utils.name
    Utils.sayHello()
}
//设置成一个单例
object Utils{
    var name = "张三"

    fun sayHello(){
        println("hello")
    }
}