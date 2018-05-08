package com.itcast.day04


/**
 * ClassName:`02.类委托`
 * Description:
 */
fun main(args: Array<String>) {
    val smallHeadFather = SmallHeadFather()
    //小头爸爸洗碗
    smallHeadFather.wash()
}
//洗碗能力
interface WashPower{
    //洗碗行为
    fun wash()
}
//大头儿子
class BigHeadSon:WashPower{
    override fun wash() {
        println("大头儿子开始洗碗了")
    }
}

//小头爸爸 将洗碗的能力委托给大头儿子
class SmallHeadFather:WashPower by BigHeadSon()