package com.itcast.day04.类委托实现二功能加强


/**
 * ClassName:`02.类委托`
 * Description:
 */
fun main(args: Array<String>) {
//    val smallHeadFather = SmallHeadFather()
    //小头爸爸洗碗
//    smallHeadFather.wash()

    //大头儿子
    val bigHeadSon = BigHeadSon()
    val smallHeadFather = SmallHeadFather(SmallHeadSon())
    smallHeadFather.wash()
}
//洗碗能力
interface WashPower{
    //洗碗行为
    fun wash()
}
class SmallHeadSon:WashPower{
    override fun wash() {
        println("小头儿子开始洗碗")
    }

}
//大头儿子
class BigHeadSon:WashPower{
    override fun wash() {
        println("大头儿子开始洗碗了")
    }
}

//小头爸爸 将洗碗的能力委托给大头儿子
//class SmallHeadFather:WashPower by BigHeadSon()
class SmallHeadFather(var washPower: WashPower):WashPower by washPower{
    override fun wash() {
        //付钱
        println("付给小头儿子1块钱")
        //小头儿子功能
        washPower.wash()
        //洗完之后
        println("干的很好  下次继续")
    }
}