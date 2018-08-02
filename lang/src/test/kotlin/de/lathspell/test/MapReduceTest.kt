package de.lathspell.test

import kotlin.test.Test
import kotlin.test.assertEquals

class MapReduceTest {

    private val colors = listOf("red", "green", "blue", "black")

    /**
     * The flatMap() function can be used to iterate over a list and add/remove elements to it on the fly.
     */
    @Test
    fun flatMap() {
        val actual = colors.flatMap {
            when {
                it.startsWith("b") -> listOf(it, it)
                it.startsWith("g") -> listOf()
                else -> listOf(it)
            }
        }
        assertEquals(listOf("red", "blue", "blue", "black", "black"), actual)
    }

    @Test
    fun reduce() {
        val actual = colors.reduce { result, value -> "$result, $value" }
        assertEquals("red, green, blue, black", actual)
    }
}