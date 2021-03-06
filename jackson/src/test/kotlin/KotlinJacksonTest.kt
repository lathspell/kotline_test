package de.lathspell.test

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinJacksonTest {

    data class Person(val name: String, val yob: Int)

    @Test
    fun test1() {
        val p = Person("Tim", 1955)

        val om = ObjectMapper()  //.registerModule(KotlinModule())
        assertEquals("""{"name":"Tim","yob":1955}""", om.writeValueAsString(p))
    }

    @Test
    fun testWithoutKotlinModule() {
        val p = Person("Tim", 1955)
        val om = ObjectMapper()
        assertEquals("""{"name":"Tim","yob":1955}""", om.writeValueAsString(p))
    }
}
