import kotlinx.coroutines.experimental.launch

/**
 * ClassName:`02.协程打印数据`
 * Description:
 */
fun main(args: Array<String>) {
    println("主线程开始执行")

    //开启协程
    launch {
        (1..10).forEach {
            println("打印了$it")
            //睡眠5秒钟
            Thread.sleep(500L)
        }
    }

    println("主线程结束执行")

    //主线程睡眠
    Thread.sleep(5000L)
}