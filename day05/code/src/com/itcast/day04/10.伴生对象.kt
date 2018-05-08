package com.itcast.day04.伴生对象


/**
 * ClassName:`10.伴生对象`
 * Description:伴生对象作用就是控制属性的静态
 */
fun main(args: Array<String>) {
    //访问age
    val person = Person()
    person.age
    //name访问
    Person.name
}

class Person {
    var age = 20

    companion object {
        //静态的name
        var name = "张三"

    }
}