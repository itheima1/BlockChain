package com.itcast.day03.封装


/**
 * ClassName:Consumer
 * Description:程序员B
 */
fun main(args: Array<String>) {
    //买一个洗衣机
    val machine = WashMachine("海尔",12)
    //打开洗衣机门
    machine.openDoor()
    //放入衣服
    println("放入牛仔裤")
    //关闭洗洗衣机门
    machine.closeDoor()
    //设置模式
    machine.mode = 2
    //点击开始洗衣服按钮
    machine.start()

//    machine.setMotorSpeed(10000)
}