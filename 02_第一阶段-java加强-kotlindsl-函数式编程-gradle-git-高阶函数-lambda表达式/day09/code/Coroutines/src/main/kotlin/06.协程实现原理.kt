import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`06.协程实现原理`
 * Description:
 */
fun main(args: Array<String>) = runBlocking {
    //线程池
    val job = launch {
        println("协程执行前:${Thread.currentThread().name}")

        //等待 sleep() 非阻塞
        delay(2000L)
//        Thread.sleep(2000L)//不能被挂起

        println("协程执行后:${Thread.currentThread().name}")
    }
    //开启 之后用的还是同一个线程池
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }
    launch {
        Thread.sleep(3000L)
    }


    job.join()
}

