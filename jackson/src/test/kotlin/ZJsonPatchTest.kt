package de.lathspell.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.flipkart.zjsonpatch.JsonDiff
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ZJsonPatchTest {

    data class Address(
            val street: String,
            val nr: Int,
            val city: String,
    )

    data class Person(
            val name: String,
            val address: Address,
    )

    private val om = ObjectMapper().registerKotlinModule()

    @Test
    fun `create patch`() {
        val max = Person(name = "Max", address = Address("Markt", 1, "Cologne"))
        val tim = Person(name = "Tim", address = Address("Markt", 2, "Cologne"))

        val expected = """[{"op":"replace","path":"/name","value":"Tim"},{"op":"replace","path":"/address/nr","value":2}]"""

        val maxNode = om.valueToTree<ObjectNode>(max)
        val timNode = om.valueToTree<ObjectNode>(tim)
        val actual = JsonDiff.asJson(maxNode, timNode)

        assertEquals(expected, actual.toString())
    }

}
