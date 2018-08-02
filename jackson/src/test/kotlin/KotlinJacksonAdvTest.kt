package de.lathspell.test

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Testing the KotlinModule extension.
 *
 * Jackson stumbles upon certain Kotlin specific things like "IntRange" and
 * generates JSON that cannot be fed into the classes constructor when deserializing
 * back into an object. An example is IntRange where Reflection finds the property "step" from
 * IntProgression which must be 1 for IntRange and is therefore not settable.
 */
class KotlinJacksonAdvTest {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonPropertyOrder(alphabetic = true)
    data class Person(val name: String, @JsonProperty("year_of_birth") val yob: Int) {
        var ignoreme: String? = null

        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        lateinit var createdAt: Date

        lateinit var socks: Pair<String, String>

        val favNums = IntRange(3, 5)
    }

    val p = Person("Tim", 1955).apply {
        createdAt = Date(1531582913700L)
        socks = Pair("left", "right")
    }

    @Test
    fun testWithKotlinModule() {
        val omPlain = ObjectMapper().registerModule(JavaTimeModule()).registerModule(KotlinModule())
        val json = omPlain.writeValueAsString(p)
        val p1: Person = omPlain.readValue(json)
        assertEquals(p, p1)
    }

    @Test(expected = InvalidDefinitionException::class)
    fun testWithoutKotlinModule() {
        val omPlain = ObjectMapper().registerModule(JavaTimeModule())
        val json = omPlain.writeValueAsString(p)
        val p1: Person = omPlain.readValue(json)
        assertEquals(p, p1)
    }


}