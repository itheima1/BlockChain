import org.junit.Assert
import org.junit.Test

/**
 * ClassName:TestNetUtils
 * Description:junit
 */
class TestNetUtils {
    @Test
    fun testSendRequest() {
        val utils = NetUtils()
        val msg = utils.sendRequest()
        Assert.assertEquals("hello", msg)
    }
}