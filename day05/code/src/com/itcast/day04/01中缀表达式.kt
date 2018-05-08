package com.itcast.day04


/**
 * ClassName:`01中缀表达式`
 * Description:中缀表达式 让代码更加简洁易懂 DSL
 * 使用条件:
 */
//顶层函数
fun sayHelloTo(name:String){

}
fun main(args: Array<String>) {
    //张三
    val 张三 = Person()

//    张三.sayHelloTo("李四")

    张三  sayHelloTo "李四"

   //自定义操作符 区间 元组 二元 三元
    val pair = Pair<String,Int>("张三",20)

    val pair2 = "张三" to 20
}
class Person{
    infix fun sayHelloTo(name:String){
        println("你好 $name")
    }
}