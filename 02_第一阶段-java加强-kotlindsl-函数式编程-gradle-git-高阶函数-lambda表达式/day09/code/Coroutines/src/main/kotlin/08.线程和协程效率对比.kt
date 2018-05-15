import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ClassName:`08.线程和协程效率对比`
 * Description:
 */
fun main(args: Array<String>) = runBlocking {
    /*---------------------------- 线程耗时 ----------------------------*/
    //开始时间
//    val startTime = System.currentTimeMillis()
//
//    val threadList = List(100000){
//        MyThread()
//    }
//    threadList.forEach { it.start() }
//    threadList.forEach { it.join() }
//    //结束时间
//    val endTime = System.currentTimeMillis()
//    //耗时
//    val time = endTime-startTime
//    println("线程耗时$time")//8962

    /*---------------------------- 协程执行 ----------------------------*/
    val startTime = System.currentTimeMillis()
    val coroutinesList = List(100000){
        launch {
            println(".")
        }
    }
    coroutinesList.forEach {
        it.join()
    }
    val endTime = System.currentTimeMillis()
    val time = endTime - startTime
    println("协程耗时$time")//918
}
class MyThread:Thread(){
    override fun run() {
        println(".")
    }
}