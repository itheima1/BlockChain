package com.itcast.day03.kk


/**
 * ClassName:`23.泛型类型投射`
 * out:接收当前类型或它的子类 相当于java的 ? extents
 * in:接收当前类型或者它的父类  相当于java的 ? super
 */
fun main(args: Array<String>) {
    val list = ArrayList<Thing>()
    setFruitList(list)
}

fun setFruitList(list:ArrayList<in Fruit>){//接收只能是Fruit Apple是Fruit子类
    println(list.size)
}

//箱子
open class Box<T>(var thing:T)//物品类型不确定  定义泛型和使用泛型


abstract class Thing
//水果
abstract class Fruit:Thing()
//苹果
class Apple:Fruit()
//橘子
class Orange:Fruit()