package com.itcast.day04


/**
 * ClassName:`08.扩展函数`
 * Description:
 */
fun main(args: Array<String>) {
    val str:String? = null
    //str是否为空
    val myIsEmpty = str.myIsEmpty()
    println(myIsEmpty)

    val son = Son()
    son.sayHello()
}
//扩展函数
/**
 * 1.String类扩展 fun String.扩展函数名
 * 2.扩展函数可以访问当前对象里面的字段和方法
 */
//扩展的非空类型的String  可不可扩展可空类型的对象?
fun String?.myIsEmpty():Boolean{
    return this==null||this.length==0
}
//扩展方法
fun Father.sayHello(){
    println("爸爸打招呼了")
}
open class Father{
    fun haha(){

    }
}
class Son:Father(){

}