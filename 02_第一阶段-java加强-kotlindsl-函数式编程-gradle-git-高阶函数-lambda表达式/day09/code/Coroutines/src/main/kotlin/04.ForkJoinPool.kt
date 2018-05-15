import java.util.concurrent.ForkJoinPool

/**
 * ClassName:`04.ForkJoinPool`
 * Description:
 */
/**
 * 普通线程池 主线程执行结束之后 可以继续执行
 * ForkJoinPool 主线程执行完之后 ForkJoinPool里面的线程也结束了
 * 普通的线程池创建的用户线程
 * ForkJoinPool创建的是守护线程
 *
 * 通过launch函数启动协程.运行在线程池中,启动的线程池默认是守护线程
 */
fun main(args: Array<String>) {

    println("主线程开始执行")
    /*---------------------------- 常规的线程池执行 ----------------------------*/
    //定义线程池
//    val service = Executors.newFixedThreadPool(3)
//    //创建Runable对象
//    val runnable = MyRunable()
//    //执行
//    service.execute(runnable)
    /*---------------------------- ForkJoinPool实现 ----------------------------*/
    val service = ForkJoinPool(3)
    //创建Runable对象
    val runnable = MyRunable()
    //执行
    service.execute(runnable)

    Thread.sleep(2000L)
    println("主线程结束执行")
}
class MyRunable:Runnable{
    override fun run() {
        (1..10).forEach {
            println(it)
            Thread.sleep(500L)
        }
    }
}