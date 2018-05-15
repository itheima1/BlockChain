package com.itcast.day03.ahha


/**
 * ClassName:`19.泛型`
 * 定义对象的时候使用泛型
 * 定义子类时候执行泛型
 * 定义子类的时候不知道具体类型,继续使用泛型
 * T:Fruit  泛型上限  泛型智能是Fruit类型或者Fruit类型的子类
 * 泛型作用:放任何类型 限制存放的类型
 */
fun main(args: Array<String>) {
    val apple = Apple()
    val thing = Thing()
//    val fruitBox = FruitBox(thing)
}
//箱子
open class Box<T>(var thing:T)//物品类型不确定  定义泛型和使用泛型

//水果箱子
class FruitBox<T:Fruit>(thing:T):Box<T>(thing)//只能水果


open class Thing
//水果
open class Fruit:Thing()
//苹果
class Apple:Fruit()
//橘子
class Orange:Fruit()