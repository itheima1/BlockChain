package com.itcast.day04.和java一样的单例


/**
 * ClassName:`11.和java一样的单例`
 * Description:
 */
fun main(args: Array<String>) {
    Utils.instan.age
//    val utils = Utils()
}

class Utils private constructor(){//第一步:私有构造函数
    //非静态的
    var age = 20

    companion object {
        //静态
        var name = "张三"
        //instance代表Utils的对象实例
        val instan by lazy { Utils() }//惰性加载  只会加载一次  线程安全
    }
}