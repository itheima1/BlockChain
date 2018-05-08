package com.itcast.ay05


/**
 * ClassName:`20.分组`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf("张三", "李四", "王五", "赵六", "张四", "李五", "李六")
    //姓张的一组 姓李的一组 其他一组
    val map = list.groupBy {
        val first = it.substring(0,1)
        when(first){
            "张"->"张"
            "李"->"李"
            else->"其他"
        }
    }
    println(map)

}