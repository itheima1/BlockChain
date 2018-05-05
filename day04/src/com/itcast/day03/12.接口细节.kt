package com.itcast.day03.heh

import com.itcast.day03.ZhHuman


/**
 * ClassName:`11.接口`
 * kolin接口里面字段不能实现
 */
fun main(args: Array<String>) {
    val xiaoMing = XiaoMing()
    xiaoMing.ride()
    xiaoMing.drive()
}
//小明
class XiaoMing: ZhHuman(),RideBike,DriveCar{
    override var license: String = "123456789"

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
    //驾照号码
    var license:String
    //开车行为
    fun drive()
}