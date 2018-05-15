package com.itcast.dsl.网络请求

import java.util.*


fun main(args: Array<String>) {
    
}

class HttpUtil {
    interface CallBack {
        fun onSuccess(result: String)
        fun onError(e: String)
    }

    fun sendRequest(path: String?, method: String?, onsuccess: ((String) -> Unit)?, onError: ((String) -> Unit)?) {
        println("pth=$path method=$method")
        Thread {
            //创建URL
//            val url = URL(path)
            println("创建URL")

            //打开网络连接
//            val conn = url.openConnection() as HttpURLConnection
            println("打开网络连接")

            //获取响应码
//            val resCode = conn.responseCode

            val code = Random().nextInt(100)
            println("获取响应码")

            if (code % 2 == 0) {
                onsuccess?.invoke("结果正确了")
            } else {
                onError?.invoke("响应码不正确")
            }
        }.start()
    }
}