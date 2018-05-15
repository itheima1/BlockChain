import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout

/**
 * ClassName:`10.协程定时取消`
 * Description:
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        withTimeout(2000L) {
            (1..10).forEach {
                println("打印$it")
                delay(500L)
            }
        }
    }

    job.join()
}