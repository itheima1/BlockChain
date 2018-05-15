import kotlinx.coroutines.experimental.launch

/**
 * ClassName:`01.第一个协程`
 * Description:
 */
fun main(args: Array<String>) {
    println("主线程开始执行")

    //开启协程
    launch {
        println("协程执行了")
    }

    println("主线程结束执行")

    //主线程睡眠
    Thread.sleep(1000L)
}