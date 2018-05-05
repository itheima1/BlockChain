package com.itcat.day02.comm


/**
 * ClassName:`12.when表达式`
 * Description:
 * when表达式返回值在{}最后一行  lambda表达式最后一行为返回值
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
    return when(age){
        7-> {
            println("找到了分支")
            10
             "开始上小学"
        }

        12->{
             "开始上中学"
        }
        15->{
             "开始上高中"
        }
        18->{
             "开始上大学"
        }
        else->{
             "开始上社会大学"
        }
    }
}