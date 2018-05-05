package com.itcast.day03.hh


/**
 * ClassName:`15.智能类型转换`
 * Description:
 */
fun main(args: Array<String>) {
    val shepHerdDog:Dog = ShepHerdDog()
    val ruralDog = RuralDog()

//    shepHerdDog.herdShep()
//    ruralDog.watchDoor()

    //想调用herdShep方法  1.判断是否是ShepHerdDog 2.转换成ShepHerdDog类型
    if(shepHerdDog is ShepHerdDog){//判断完之后已将将shepHerdDog类型由Dog类型转换为ShepHerdDog类型
        //2.转换成ShepHerdDog类型
//        val newDog = shepHerdDog as ShepHerdDog //as强转

//        newDog.herdShep()
        shepHerdDog.herdShep()
    }
}
//Dog狗
abstract class Dog
//牧羊犬
class ShepHerdDog:Dog(){
    //功能
    fun herdShep(){
        println("牧羊犬放羊")
    }
}
//中华田园犬
class RuralDog:Dog(){
    //功能
    fun watchDoor(){
        println("中华田园犬看家")
    }
}
