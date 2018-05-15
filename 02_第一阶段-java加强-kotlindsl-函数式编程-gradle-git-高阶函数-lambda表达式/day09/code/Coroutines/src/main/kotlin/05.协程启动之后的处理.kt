import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`05.协程启动之后的处理`
 * Description:
 */
fun main(args: Array<String>) = runBlocking{
    println("主线程开始执行")

//    val job = launch {
//        println("协程执行了")
//    }

    val job = launch {
        (1..10).forEach {
            println("打印了$it")
            //睡眠5秒钟
            Thread.sleep(500L)
        }
    }

    println("主线程结束执行")
    //解决方案1:主线程睡眠
//    Thread.sleep(100L)
    //解决方案2:通过协程返回的Job任务执行join方法(main runblocking)
    job.join()
}