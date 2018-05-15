package com.itcast.day06.dslclass.构建器


/**
 * ClassName:`01.StandClass`
 * Description:字段可以赋值多次  需要只能赋值一次
 * 创建Person时不能传递字段  就需要默认值null  后面不能修改了
 * 创建Person时必须要传递字段
 * Person可以等到所有的字段都有了之后再创建
 */
fun main(args: Array<String>) {
    val person: Person =
            person {
                name = "徐熊丽"
                age = 30
                addresses {
                   address {
                       city = "深圳"
                       street = "宝安街道"
                       number = 114
                   }
                    address {
                        city = "广东"
                        street = "流线街道"
                        number = 110
                    }
                }
            }


    println(person)
}

fun MYLIST.address(block:AddressBuilder.()->Unit){
    //Address对象
    val address = AddressBuilder()
    //执行block函数添加属性
    address.block()
    //添加到集合中
    add(address.builder())
}

//address高阶函数
//相当于在Person里面又定义了一个address函数
fun PersonBuilder.addresses(block: MYLIST.() -> Unit) {
    //需要创建Address对象 赋值给person的addres字段
    val list = MYLIST()
    //执行block函数
    list.block()
    //必须要讲address字段赋值给person函数里面定义的person对象
    this.address = list

//    this.address = Address().apply(block)
}

//普通函数 函数参数是函数
/**
 * 第一步:实现person函数
 * 函数参数可能有一个参数 先不写
 * 函数参数可能有返回值  先不写
 */
fun person(block: PersonBuilder.() -> Unit): Person {//第二步:实现name属性可以添加个person的perosn对象 接收者为Person对象的函数
    val person = PersonBuilder()//不能传参数  需要通过里面的dsl获取
    //第三步:执行block函数
    person.block()

    return person.build()

    //apply  let  with run

}

//地址
data class Address(val city: String?, val street: String?, val number: Int?)

//对象
data class Person(val name: String?, val age: Int?, val address:MYLIST?)


@MYCLASS
class MYLIST:ArrayList<Address>()

//定义PersonBuilder
@MYCLASS
class PersonBuilder {
    var name: String? = null
    var age: Int? = null
    var address: MYLIST? = null
    fun build(): Person {
        return Person(name, age, address)
    }
}
@MYCLASS
class AddressBuilder{
    var city:String?=null
    var street:String? = null
    var number:Int? = null
    fun builder():Address{
        return Address(city,street,number)
    }
}

@DslMarker
annotation class MYCLASS