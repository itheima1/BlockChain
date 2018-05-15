import org.junit.Assert
import org.junit.Test

/**
 * ClassName:TestService
 * Description:
 */
class TestService {
    @Test
    fun testGetMessage(){
        val msgService = NetService()
        val msg = msgService.sendRequest()
        Assert.assertEquals("hello world",msg)
    }
}