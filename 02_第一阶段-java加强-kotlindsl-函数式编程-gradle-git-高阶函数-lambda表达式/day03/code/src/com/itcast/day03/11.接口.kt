package com.itcast.day03


/**
 * ClassName:`11.接口`
 * Description:
 */
fun main(args: Array<String>) {
    val xiaoMing = XiaoMing()
    xiaoMing.ride()
    xiaoMing.drive()
}
//小明
class XiaoMing:ZhHuman(),RideBike,DriveCar{
    override fun drive() {
        println("小明学会了开车")
    }

    override fun ride() {
        println("小明学会了骑自行车")
    }
}
//能力用接口表示
interface RideBike{
    //骑自行车行为
    fun ride()
}

//开车能力
interface DriveCar{
    //开车行为
    fun drive()
}