package com.itcast.day03.封装


/**
 * ClassName:WashMachine
 * Description:程序员A洗衣机类型
 */
class WashMachine(var brand: String, var l: Int) {
    //保存模式
    var mode = 0//默认模式 1 轻柔  2 狂揉

    //开门动作
    fun openDoor() {
        println("打开洗衣机门...")
    }

    //关闭洗衣机门
    fun closeDoor() {
        println("关闭洗衣机门...")
    }

    //开始洗衣服的按钮
    fun start() {
        when (mode) {
            0 -> {
                println("请选择模式")
            }
            1 -> {
                println("开始放水...")
                println("水放满了...")
                println("开始洗衣服...")
                println("洗衣服模式为轻柔")
                setMotorSpeed(1000)
                println("衣服洗好了...")
            }
            2 -> {
                println("开始放水...")
                println("水放满了...")
                println("开始洗衣服...")
                println("洗衣服模式为狂柔")
                setMotorSpeed(10000)
                println("衣服洗好了...")
            }
            else->{
                println("模式设置错误")
            }
        }

    }

    //调节转速操作
    private fun setMotorSpeed(speed:Int){
        println("当前转速为$speed")
    }

    //设置模式(用户调节模式)
    fun selectMode(mode: Int) {
        this.mode = mode
    }
}