package com.itcast.day01


/**
 * ClassName:`18.字符串模板`
 * Description:
 */
fun main(args: Array<String>) {
    val result = createDiary("天安门")
    println(result)
}
//生成日记模板
fun createDiary(place:String):String{
//    val result = "今天天气晴朗，万里无云，我们去"+place+"游玩，首先映入眼帘的是，"+place+place.length+"个镏金大字"
    val result = "今天天气晴朗，万里无云，我们去${sayHello1()} 游玩，首先映入眼帘的是，${place}${place.length}个镏金大字"
    return result
}
fun sayHello1():String{
    return "halleo"
}