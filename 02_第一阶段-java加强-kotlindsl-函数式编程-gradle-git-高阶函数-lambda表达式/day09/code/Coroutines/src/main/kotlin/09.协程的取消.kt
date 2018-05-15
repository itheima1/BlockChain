import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`09.协程的取消`
 * Description:
 */
fun main(args: Array<String>)= runBlocking {
    val job = launch {
        (1..10).forEach {
            println("打印$it")
            delay(500L)
        }
    }
    //定时2秒钟  停止协程
    delay(2000L)
    //取消协程
    job.cancel()

    job.join()
}