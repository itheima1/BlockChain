import org.junit.Test

/**
 * ClassName:TestHashUtil
 * Description:
 */
class TestHashUtil {
    @Test
    fun testUtil(){
        val str = "hello"
        val result = HashUtil.getSHA256StrJava(str)
        println(result)
    }
}