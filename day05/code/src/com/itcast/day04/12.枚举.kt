package com.itcast.day04


/**
 * ClassName:`12.枚举`
 * Description:
 */
fun main(args: Array<String>) {
    //星期一
    println(WEEK.星期一)
    println(WEEK.星期二)
    //所有的元素全部找到
    val result = WEEK.values()
    result.forEach { println(it) }
    //when switch
    println(todo(WEEK.星期四))
    println(todo(WEEK.星期日))
}
fun todo(week: WEEK){
    when(week){
        in WEEK.星期一..WEEK.星期五-> println("上班")
//        WEEK.星期二-> println("上班")
//        WEEK.星期三-> println("上班")
//        WEEK.星期四-> println("上班")
//        WEEK.星期五-> println("上班")
        WEEK.星期六,WEEK.星期日-> println("休息")
//        -> println("休息")

    }
}
//一周枚举
enum class WEEK{//数据
    星期一,星期二,星期三,星期四,星期五,星期六,星期日
}