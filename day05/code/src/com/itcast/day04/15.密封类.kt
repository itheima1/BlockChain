package com.itcast.day04


/**
 * ClassName:`15.密封类`
 * Description:
 */
fun main(args: Array<String>) {
    val startk1 = JonSnow3()
    val h1 = hasRight(startk1)
    println(h1)
}

/**
 * 判断有没有继承权
 * 先把所有有继承权的(数量固定) 放在一起  其他都不用管了
 */
fun hasRight(startk:NedStark):Boolean{
    return when(startk){
        is NedStark.AryaStark->true
        is NedStark.SansaStark->true
        is NedStark.RobStark->true
        is NedStark.BrandonStark->true
        else->false
    }
}

//斯塔克
sealed class NedStark{//密封类封装的是类型 类型是确定的
    class RobStark : NedStark()

    class SansaStark : NedStark()

    class AryaStark : NedStark()

    class BrandonStark : NedStark()
}

class JonSnow : NedStark()
class JonSnow2 : NedStark()
class JonSnow3 : NedStark()
class JonSnow4 : NedStark()



