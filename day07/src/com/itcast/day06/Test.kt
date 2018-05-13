package com.itcast.day06

import java.net.HttpURLConnection
import java.net.URL


/**
 * ClassName:Test
 * Description:
 */
fun main(args: Array<String>) {
    val url = URL("http:www.baidu.com")
    val conn = url.openConnection() as HttpURLConnection
    val code = conn.responseCode
    if(code==200){

    }else{

    }
}