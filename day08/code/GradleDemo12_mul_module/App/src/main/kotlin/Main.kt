/**
 * ClassName:Main
 * Description:
 */
fun main(args: Array<String>) {
    val utils = NetUtils()
    val msg = utils.sendRequest()
    println(msg)
}