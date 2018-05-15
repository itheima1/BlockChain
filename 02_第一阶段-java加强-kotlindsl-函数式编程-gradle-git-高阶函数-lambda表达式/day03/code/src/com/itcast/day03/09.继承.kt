package com.itcast.day03


/**
 * ClassName:`09.继承`
 * Description:
 */
fun main(args: Array<String>) {
    val son = Son()
    println(son.name)
    println(son.age)
    son.horbe()
}
//kotlin的类都是final的  不能被继承
open class Father{
    open var name = "张三"
    open var age = 20
    //动态行为
    open fun horbe(){
        println("父亲喜欢抽烟")
    }
}
//子承父业
class Son:Father(){
    override var name: String = "张四"
    override var age: Int = 10
    override fun horbe() {
//        super.horbe()
        println("孩子喜欢看书")
    }
}