package com.itcast.day03


/**
 * ClassName:`20.泛型函数`
 * Description:
 */
fun main(args: Array<String>) {
    parseType(10)
    parseType("张三")
    parseType(Person())

}

//方法 thing  判断类型
fun <T> parseType(thing: T) {//前面定义泛型  后面使用泛型
    when (thing) {
        is Int -> println("是Int类型")
        is String -> println("是String类型")
        else -> println("不知道具体类型")
    }
}