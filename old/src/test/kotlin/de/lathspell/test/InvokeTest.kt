package de.lathspell.test

import main.kotlin.de.lathspell.test.InvokeEx
import org.junit.Test
import kotlin.test.assertEquals

class InvokeTest {

    @Test
    fun testInvokeOperator() {
        val result = InvokeEx()
        result()
        assertEquals(listOf("ctor", "invoked"), result.log)
    }

}