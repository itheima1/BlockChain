package com.itcast.day06


/**
 * ClassName:`01.StandClass`
 * Description:
 */
fun main(args: Array<String>) {
//    val address = Address("深圳市","宝安街道",114)
//    val person = Person("林青霞",30,address)
//
//    val address = Address("深圳市","宝安街道",114)
//    val person = Person("林青霞",30,address)
//
//    val address = Address("深圳市","宝安街道",114)
//    val person = Person("林青霞",30,address)

    /*
    person{
        name = "徐熊丽"
        age = 20
        address{
            city = "深圳市"
            street = "宝安街道"
            number = 114
        }
    }
    */


}
//地址
class Address(var city:String,var street:String,var number:Int)
//对象
class Person(var name:String,var age:Int,var address: Address)