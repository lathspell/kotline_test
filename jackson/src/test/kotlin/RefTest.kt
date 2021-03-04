package de.lathspell.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RefTest {

    private val om = ObjectMapper().registerKotlinModule()

    data class Person(val firstName: String)

    private inline fun <reified T> create(params: Map<String, Any>): T {
        return om.convertValue(params, T::class.java)
    }

    @Test
    fun test1() {
        assertEquals(Person("Max"), create<Person>(mapOf("firstName" to "Max")))
    }

}
