package com.itcast.day03.haha


/**
 * ClassName:`01.get和set`
 * koltin字段是私有的  会自动生成get和set方法
 */
fun main(args: Array<String>) {
    val person = Person()
    println(person.name)
//    person.name = "李四"
    println(person.age)
}
//直接访问还是get和set方法
class Person{
    var name  = "张飒"//只能访问不能修改
    private set//私有了set方法

    var age = 20

}