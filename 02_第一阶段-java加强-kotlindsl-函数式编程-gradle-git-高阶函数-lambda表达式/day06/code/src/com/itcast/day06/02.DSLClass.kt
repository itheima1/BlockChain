package com.itcast.day06.dslclass



/**
 * ClassName:`01.StandClass`
 * Description:
 */
fun main(args: Array<String>) {
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

    val person: Person =
            person {
                name= "徐熊丽"
                age = 30

                address{
                    city = "深圳"
                    street = "宝安街道"
                    number = 114
                }
            }
    println(person)
}

//address高阶函数
//相当于在Person里面又定义了一个address函数
fun Person.address(block:Address.()->Unit){
    //需要创建Address对象 赋值给person的addres字段
//    val address = Address()
    //执行block函数
//    address.block()
    //必须要讲address字段赋值给person函数里面定义的person对象
//    this.address = address

    this.address = Address().apply(block)
}

//普通函数 函数参数是函数
/**
 * 第一步:实现person函数
 * 函数参数可能有一个参数 先不写
 * 函数参数可能有返回值  先不写
 */
fun person(block:Person.()->Unit):Person{//第二步:实现name属性可以添加个person的perosn对象 接收者为Person对象的函数
//    val person = Person()//不能传参数  需要通过里面的dsl获取
    //第三步:执行block函数
//    person.block()
//    return person

    //apply  let  with run
    return Person().apply(block)
}

//地址
data class Address(var city: String?=null, var street: String?=null, var number: Int?=null)

//对象
data class Person(var name: String?=null, var age: Int?=null, var address: Address?=null)