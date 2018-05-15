
import kotlinx.coroutines.experimental.*

/**
 * ClassName:`14.协程上下文`
 * Description:
 */
fun main(args: Array<String>)= runBlocking {
    //第一个代表协程上下文 指定协程代码在哪个线程池中运行 默认是通过Commpool实现的
    val job1 = launch {
        println("协程执行")
    }
    val job2 = launch(CommonPool) {
        println("协程执行")
    }
    val job3 = launch(Unconfined) {//运行在主线程中
        println("协程执行")
    }
    val job4 = launch(coroutineContext) {//运行在父协程的上下文中 当前运行在主线程中
        println("协程执行")
    }
    val job5 = launch(newFixedThreadPoolContext(5,"新线程")) {//自定义线程池执行
        println("协程执行")
    }
    job1.join()
    job2.join()
}