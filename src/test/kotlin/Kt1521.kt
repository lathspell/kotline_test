import org.junit.Assert.assertEquals
import org.junit.Test

class Kt1521 {

    /**
     * Unexpected behaviour of "if".
     *
     * This is no bug - if without else does not make sense as expression and
     * therefore yields always to NULL aka "Unit" and the textual representation
     * of Unit is "()". It's arguable if this shouldn't rather result in a compiler
     * error or at least a warning.
     */
    @Test
    fun testKt1521() {
        // 2017-10-25: It's now a compiler error!
        // val ex1 = "aa" + (if (true) "foo" + "bar") + "zz"
        // assertEquals("aa()zz", ex1)
        val ex2 = "aa" + (if (true) "foo" + "bar" else "F") + "zz"
        assertEquals("aafoobarzz", ex2)
    }
}
