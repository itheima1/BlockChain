package com.itcast.day03


/**
 * ClassName:`19.泛型`
 * 定义对象的时候使用泛型
 * 定义子类时候执行泛型
 * 定义子类的时候不知道具体类型,继续使用泛型
 */
fun main(args: Array<String>) {
    //用箱子
    val box =Box<String>("张三")
    println(box.thing)
}
//箱子
open class Box<T>(var thing:T)//物品类型不确定  定义泛型和使用泛型


//水果箱子
class FruitBox(thing:Fruit):Box<Fruit>(thing)
//不知道具体放什么东西
class SonBox<T>(thing:T):Box<T>(thing)//第一个是申明  后面两个都是使用

//水果
abstract class Fruit
//苹果
class Apple:Fruit()
//橘子
class Orange:Fruit()