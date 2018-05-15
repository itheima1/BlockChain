package com.itcat.day02


/**
 * ClassName:`12.when表达式`
 * Description:分支可以支持区间  多条件判断
 *
 * kotlin里面的when表达式原理:
 * 简单的when表达式通过switch语句实现
 * 复杂的when表达式通过if else来实现
 */
fun main(args: Array<String>) {
    //7岁 开始上小学
    //12岁 开始上中学
    //15岁 开始上高中
    //18岁  开始上大学
    val age = 20
    //小功能
    val result = todo1(age)
    println(result)
}

/**
 * 判断当前年纪做什么事情
 */
fun todo1(age:Int):String{
    when(age){
//        1-> return "没有上小学"
//        2-> return "没有上小学"
//        3-> return "没有上小学"
//        4-> return "没有上小学"
//        5-> return "没有上小学"
//        6-> return "没有上小学"

//        1,2,3,4,5,6-> return "没有上小学"
//        is Int->return "传递的是Int类型"//java判断类型  instanceof

        //在区间里面
        in 1..6-> return "没有上小学"

        7-> return "开始上小学"
        in 8..11->return "在上小学"

        12->{
            return "开始上中学"
        }
        13,14->return "正在上中学"
        15->{
            return "开始上高中"
        }
        16,17->return "正在上高中"
        18->{
            return "开始上大学"
        }
        in 19..22->return "正在上大学"
        else->{
            return "开始上社会大学"
        }
    }
}