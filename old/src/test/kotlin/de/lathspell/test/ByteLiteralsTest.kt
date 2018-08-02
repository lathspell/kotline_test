package de.lathspell.test

import org.junit.Test
import kotlin.test.assertEquals

class ByteLiteralsTest {

    @Test
    fun testByteLiteral() {
        val b: Byte = 0x10
        val s: Short = 0xE4
        assertEquals(16, b)
        assertEquals(228, s)
    }

}