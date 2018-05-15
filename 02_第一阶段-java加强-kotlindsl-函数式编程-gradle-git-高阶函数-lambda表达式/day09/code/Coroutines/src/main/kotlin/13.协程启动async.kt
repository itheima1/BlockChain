
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`13.协程启动async`
 * Description:
 * 第一中启动协程方式:launch 没有返回值
 * 第二种启动协程方式:async 需要返回值就是用async
 */
fun main(args: Array<String>) = runBlocking{
//    val job = launch {
//        job1()
//    }
//    println("获取到了job1返回值")

    /*---------------------------- async启动协程  返回值 ----------------------------*/
    //Deferred 是job的子类
    val job1 = async { job1() }
    val job2 = async { job2() }
    val result1 = job1.await()//执行完协程之后才能获取到数据
    val result2 = job2.await()
    println(result1)
    println(result2)

}
suspend fun job1():String{
    println("开始执行job1")
    delay(1000L)
    println("执行了job1")
    return "job1"
}
suspend fun job2():String{
    println("开始执行job2")
    delay(1000L)
    println("执行了job2")
    return "job2"
}