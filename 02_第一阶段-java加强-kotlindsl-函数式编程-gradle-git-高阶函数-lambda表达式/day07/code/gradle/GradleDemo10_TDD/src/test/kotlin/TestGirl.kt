import org.junit.Assert
import org.junit.Test

/**
 * ClassName:TestGirl
 * Description:
 */
class TestGirl {
    @Test
    fun testGreeting() {
        val girl = Girl()
        val msg = girl.greeting()
        Assert.assertEquals("hello",msg)
    }
}