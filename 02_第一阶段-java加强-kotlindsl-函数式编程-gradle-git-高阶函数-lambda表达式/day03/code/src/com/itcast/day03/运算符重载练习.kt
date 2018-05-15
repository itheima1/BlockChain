package com.itcast.day03


/**
 * ClassName:运算符重载练习
 * Description:
 */
fun main(args: Array<String>) {
    var 助教  = Teacher()
    println("助教level=${助教.level} 薪资=${助教.salary}")
    var 讲师 = 助教++
    println("讲师level=${讲师.level} 薪资=${讲师.salary}")
    var 副教授 = 讲师++
    //助教  讲师  副教授是不是统一个对象?
}

/**
 * 找到++对应的运算符方法 inc
 */
class Teacher{
    //等级
    var level = 1
    //薪资
    var salary = 6000
    //实现inc
    operator fun inc():Teacher{
        this.level ++
        this.salary += 1000
        return this
    }
}