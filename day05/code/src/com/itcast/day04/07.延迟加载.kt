package com.itcast.day04.延迟加载

/**
 * lateinit:延迟加载  用的时候再赋值  不赋值不能用  程序错误处理
 */
lateinit var name1:String
fun main(args: Array<String>) {
    val person  = Person()
//    println(person.name1)
    name1 = "haha"
    println(name1)
}
class Person{
    //不确定 后面可能用的时候才会赋值  不知道具体是什么值

//    fun setName(name:String){
//        this.name1 = name
//    }
}

/**
 *1.by lazy 和lateinit  都可以单独使用或者放到成员变量中使用
 * 2.by lazy 知道具体值   用的时候再加载
 * 3.lateinit  不知道具体值  后面再赋值
 * 4.by lazy变量必须通过val修饰  lateinit需要通过var修饰
 */