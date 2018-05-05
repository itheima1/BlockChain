package com.itcast.day03.l


/**
 * ClassName:`07.次构函数`
 * Description:
 */
fun main(args: Array<String>) {

}
class Person(var name:String,var age:Int){//必须要有的属性
    var phone = ""
    //次构函数
    constructor(name:String,age:Int,phone:String):this(name, age){
        this.phone = phone
    }
}