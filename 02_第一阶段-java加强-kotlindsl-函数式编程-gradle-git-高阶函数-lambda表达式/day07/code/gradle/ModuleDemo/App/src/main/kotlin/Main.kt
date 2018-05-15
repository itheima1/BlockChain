import org.apache.log4j.Logger

/**
 * ClassName:HelloWorld
 * Description:
 */
val logger = Logger.getLogger("HelloKt")
fun main(args: Array<String>) {
    val msgService = NetService()
    val msg = msgService.sendRequest()
    logger.info(msg)
    println(msg)
}