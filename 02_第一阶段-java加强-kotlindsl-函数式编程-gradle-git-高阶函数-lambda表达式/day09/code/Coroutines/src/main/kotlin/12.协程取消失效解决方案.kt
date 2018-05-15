
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`11.协程取消失效`
 * Description:
 * 判断isActivie状态  根据这个状态再执行协程的代码
 */
fun main(args: Array<String>) = runBlocking{
    val job = launch {
        //协程里面可以获取isActivie状态

        (1..10).forEach {
            if(!isActive)return@launch//返回协程
            println("打印$it")
            Thread.sleep(500L)
        }
    }
    //定时2秒钟  停止协程
    delay(2000L)

    //协程状态
    println("取消之前${job.isActive}")

    //取消协程
    job.cancel()//取消挂起  不能取消阻塞的Thread.sleep

    println("取消之后${job.isActive}")

    job.join()
}