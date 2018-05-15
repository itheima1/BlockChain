import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`11.协程取消失效`
 * Description:
 */
fun main(args: Array<String>) = runBlocking{
    val job = launch {
        (1..10).forEach {
            println("打印$it")
            Thread.sleep(500L)
        }
    }
    //定时2秒钟  停止协程
    delay(2000L)
    //取消协程
    job.cancel()//取消挂起  不能取消阻塞的Thread.sleep

    job.join()
}