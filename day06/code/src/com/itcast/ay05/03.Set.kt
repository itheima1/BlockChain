package com.itcast.ay05


/**
 * ClassName:`03.Set`
 * Description:
 */
fun main(args: Array<String>) {
    //set集合 不能存放重复的元素
    //林青霞  高圆圆  柳岩  高圆圆
    /*---------------------------- setof ----------------------------*/
    val set1 = setOf("林青霞","高圆圆","柳岩","高圆圆")
    //修改
//    set1.
    //添加
//    println(set1)
    /*---------------------------- 可写可修改   ----------------------------*/
    //Treeset元素需要实现Comparable比较接口
    val set2 = mutableSetOf("林青霞","高圆圆","柳岩","高圆圆")
    //添加
    set2.add("刘诗诗")

    //修改
//    set2.
    println(set2)
    /*---------------------------- java的set集合 ----------------------------*/
//    hashSetOf<>()
//    val treeSet = TreeSet<String>()
//    treeSet.add("z")
//    treeSet.add("f")
//    treeSet.add("e")
//    treeSet.add("a")
//
//    println(treeSet)

//    val treeSet2 = TreeSet<Person>()
//    treeSet2.add(Person("林青霞",20))
//    treeSet2.add(Person("张曼玉",30))
//    treeSet2.add(Person("张三",60))
//    println(treeSet2)
}
//class Person(var name:String,var age:Int)