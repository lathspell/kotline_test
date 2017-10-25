import org.junit.Test
import kotlin.test.assertEquals

class ByteLiteralTest {

    @Test
    fun testByteLiteral() {
        val b: Byte = 0x10
        val s: Short = 0xE4
        assertEquals(16, b)
        assertEquals(228, s)
    }

}
