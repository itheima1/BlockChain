import kotlinx.coroutines.experimental.launch

/**
 * ClassName:`03.launch的参数和返回值`
 * Description:
 */
fun main(args: Array<String>) {
    //协程启动
    //launch是一个函数 协程需要通过launch函数启动
    //launch前三个参数都是默认参数 参数值可以不指定
    //最后一个参数是函数类型 调用的时候通过lambda表达式接收
    //launch函数的返回值是Job类型 就是协程的任务


    /**
     * launch函数第一个参数:协程上下文
     * context: CoroutineContext = DefaultDispatcher
     * context类型是CoroutineContext默认值就是DefaultDispatcher
     * DefaultDispatcher就是CommonPool
     * 第一个参数默认情况下就是赋值为CommonPool
     * CommonPool实现原理是通过ForkJoinPool实现的
     * ForkJoinPool是什么东西?
     * ForkJoinPool就是线程池
     * 协程其实也是在线程里面执行
     * 第一个参数就是制定协程执行在哪个线程或者线程池
     */
    launch {
        println("协程执行了")
    }

    Thread.sleep(1000L)
}