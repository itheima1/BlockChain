package com.itcast.day03


/**
 * ClassName:`14.多态`
 * 多态特点:通过父类接收  执行的是子类的方法
 */
fun main(args: Array<String>) {
    //创建狗和猫的对象
    val dog: Animal = Dog()
    val cat = Cat()
    dog.call()
//    cat.call()
}

//动物
abstract class Animal {
    var color: String = ""
    //行为
    open fun call() {
        println("动物叫")
    }
}

//狗
class Dog : Animal() {
    override fun call() {
        println("狗汪汪叫")
    }
}

//猫
class Cat : Animal() {
    override fun call() {
        println("猫喵喵叫")
    }

}