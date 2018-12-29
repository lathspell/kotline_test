package de.lathspell.test

import kotlin.test.Test
import kotlin.test.assertEquals


class StringMethodsTest {

    @Test
    fun testConcat() {
        assertEquals("foo", "f" + "o" + "o")
    }

    // inline fun String.replaceFirst(regex: String, replacement : String) = (this as java.lang.String).replaceFirst(regex, replacement).sure()
    @Test
    fun testReplaceFirst() {
        assertEquals("fooBARbazbar", "foobarbazbar".replaceFirst("bar", "BAR"))
    }

    @Test
    fun testCharAt() {
        // older version: inline fun String.charAt(index: Int) : Char = (this as java.lang.String).charAt(index)
        // public override fun get(index: Int): Char
        assertEquals('3', "12345".get(2))
    }

}