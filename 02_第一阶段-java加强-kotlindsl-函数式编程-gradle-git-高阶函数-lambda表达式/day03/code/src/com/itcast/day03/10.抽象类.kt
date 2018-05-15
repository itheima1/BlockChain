package com.itcast.day03


/**
 * ClassName:`10.抽象类`
 * Description:
 * 抽象类以abstract表示
 * 抽象类可以没有抽象方法和抽象字段
 */
fun main(args: Array<String>) {
    val zhHuman = ZhHuman()
    val usHuman = UsHuman()
    val afHuman = AfHuman()
    println(zhHuman.color)
    println(zhHuman.language)
}
//人类 抽象类不用open关键字
abstract class Human{
    //肤色
     abstract var color:String
    //语言
     abstract var language:String
    //吃饭
     abstract fun eat()
}
//中国人
open class ZhHuman:Human(){
    override var color: String = "黄色"
    override var language: String = "中文"

    override fun eat() {
        println("用筷子吃饭")
    }
}
//美国人
class UsHuman:Human(){
    override var color: String = "白色"
    override var language: String = "英文"

    override fun eat() {
        println("用刀叉吃饭")
    }
}
//非洲人
class AfHuman:Human(){
    override var color: String = "黑色"
    override var language: String = "葡萄牙语"

    override fun eat() {
        println("用受抓恩希玛")
    }
}