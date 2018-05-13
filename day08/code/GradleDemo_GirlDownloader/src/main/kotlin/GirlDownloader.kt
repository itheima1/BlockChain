import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.httpclient.methods.GetMethod
import java.io.File
import java.io.FileOutputStream

/**
 * ClassName:GirlDownloader
 * Description:
 */
//var path = "http://t1.mmonly.cc/uploads/tu/201703/54/121.jpg"
fun main(args: Array<String>) {
//		创建一个实例HttpClient。
    val client = HttpClient()

    (1..5).forEach {

        //		创建一个方法的实例（在这种情况下为GetMethod）。连接的URL被传递给方法构造函数。
        val method = GetMethod("http://t1.mmonly.cc/uploads/tu/201703/54/12${it}.jpg")
//		告诉HttpClient执行该方法。
        try {
            //获取响应码
            val code = client.executeMethod(method)
            if (code == HttpStatus.SC_OK) {
                //获取数据
                val responseBody = method.getResponseBody()
                //创建流读取byte数组
                val fos = FileOutputStream(File("${it}.jpg"))
                fos.write(responseBody)
            }
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } finally {
            method.releaseConnection()
        }
    }


//		阅读回复。
//		释放连接。
//		处理响应。
}