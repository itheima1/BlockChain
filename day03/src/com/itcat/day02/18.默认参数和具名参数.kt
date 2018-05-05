package com.itcat.day02


/**
 * ClassName:`18.默认参数和具名参数`
 * Description:
 */
fun main(args: Array<String>) {
//    sendRequest("http://www.baidu.com","GET")
//    sendRequest("http://www.baidu.com")
    sendRequest(method = "GET",path = "http://www.baidu.com")//具名参数 参数位置可以变化
}
fun sendRequest(path:String,method:String="GET"){//GET POST 默认参数
    println("请求路径=${path},method=${method}")
}