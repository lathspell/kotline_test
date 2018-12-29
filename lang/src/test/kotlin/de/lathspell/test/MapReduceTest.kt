package de.lathspell.test

import kotlin.test.Test
import kotlin.test.assertEquals

class MapReduceTest {

    private val colors = listOf("red", "green", "blue", "black")

    @Test
    fun map() {
        assertEquals(listOf(3, 5, 4, 5), colors.map { it.length })
    }

    @Test
    fun reduce() {
        val actual = colors.reduce { result, value -> "$result, $value" }
        assertEquals("red, green, blue, black", actual)
    }


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

    /**
     * Fold combines all values to a single one.
     * "acc" stands for "accumulator"
     * "it" stands for "iterator"
     */
    @Test
    fun fold() {
        val numbers = listOf(4, 8, 13)
        assertEquals(29, 4 + numbers.sum())
        assertEquals(29, numbers.fold(4) { acc, it -> acc + it })

        // find the greatest number
        assertEquals(13, numbers.fold(numbers.first()) {max, it -> if(it>max) it else max})
    }
}