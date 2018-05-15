package com.itcat.day02


/**
 * ClassName:`12.when表达式`
 * Description:
 */
fun main(args: Array<String>) {
    //7岁 开始上小学
    //12岁 开始上中学
    //15岁 开始上高中
    //18岁  开始上大学
    val age = 6
    //小功能
    val result = todo(age)
    println(result)
}

/**
 * 判断当前年纪做什么事情
 */
fun todo(age:Int):String{
    when(age){
        7-> return "开始上小学"

        12->{
            return "开始上中学"
        }
        15->{
            return "开始上高中"
        }
        18->{
            return "开始上大学"
        }
        else->{
            return "开始上社会大学"
        }
    }
}