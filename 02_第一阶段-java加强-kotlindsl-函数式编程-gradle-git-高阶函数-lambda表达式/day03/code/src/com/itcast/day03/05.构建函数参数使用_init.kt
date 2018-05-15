package com.itcast.day03.hehe.heh


/**
 * ClassName:`04.构建函数`
 * Description:
 */
fun main(args: Array<String>) {
    val person1 = Person("张三",20)
//    val person2 = Dats()
//    person1.name

}
class Person(name:String,age:Int){//创建的时候就需要修改里面的name和age
    var name:String = ""

    var age:Int = 0
    //构造函数中写的代码可以放到init中执行
    init {
        this.name = name
        this.age = age
    }
}

