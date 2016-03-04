package de.lathspell.test.kotlin

import org.junit.Test
import org.junit.Assert.*

// inline fun String.replaceFirst(regex: String, replacement : String) = (this as java.lang.String).replaceFirst(regex, replacement).sure()

// inline fun String.charAt(index: Int) : Char = (this as java.lang.String).charAt(index)

class StringMethodsTest {

    Test
    fun testReplaceFirst() {
        assertEquals("fooBARbazbar", "foobarbazbar".replaceFirst("bar", "BAR"))
    }

    Test
    fun testCharAt() {
        assertEquals('3', "12345".charAt(2));

    }

}
