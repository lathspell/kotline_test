package de.lathspell.test

import main.kotlin.de.lathspell.test.PowerOfTwo
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PowerOfTwoTest {
    @Test
    fun test() {
        assertTrue(PowerOfTwo.isPowerOfTwo(0))
        assertTrue(PowerOfTwo.isPowerOfTwo(1))
        assertTrue(PowerOfTwo.isPowerOfTwo(2))
        assertTrue(PowerOfTwo.isPowerOfTwo(2048))

        assertFalse(PowerOfTwo.isPowerOfTwo(3))
        assertFalse(PowerOfTwo.isPowerOfTwo(5))
        assertFalse(PowerOfTwo.isPowerOfTwo(2047))
    }
}