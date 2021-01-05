package de.lathspell.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity

/**
 * Testing MIME Multipart/mixed response.
 *
 * This was a demo for the usage of WebClient, we are therefore not using WebTestClient.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebFluxTest(
    @LocalServerPort port: Int
) {
    private val baseUri = "http://localhost:$port/"
    private val client = WebClient.create(baseUri)

    // Result is: {"customer.txt":"A long description...","customer.json":{"id":"X123"},"customer.png":"UE5HAAE="}
    val expected = mapOf(
        "customer.txt" to "A long description...",
        "customer.json" to mapOf("id" to "X123"),
        "customer2.json" to "ewogICJpZCIgOiAiWDEyMyIKfQ==",
        "customer.png" to "UE5HAAE="
    )

    @Test
    fun `receive response as object tree`() {
        val response = client.put().uri("/produce").accept(APPLICATION_JSON)
            .retrieve()
            .toEntity<Map<String, Any>>()
            .block()!!

        assertThat(response.statusCodeValue).isEqualTo(200)

        assertThat(response.body!!["customer.json"]).isInstanceOf(Map::class.java)
        assertThat((response.body!!["customer.json"] as Map<*, *>)["id"]).isEqualTo("X123")

        assertThat(response.body!!["customer2.json"]).isEqualTo("ewogICJpZCIgOiAiWDEyMyIKfQ==")
    }

    @Test
    fun `receive response as auto-encoded base64`() {
        val response = client.put().uri("/produce-base64").accept(APPLICATION_JSON)
            .retrieve()
            .toEntity<Map<String, ByteArray>>()
            .block()!!

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(response.body!!.keys).containsExactlyInAnyOrder("customer.txt")
        assertThat(response.body!!["customer.txt"]).isEqualTo("Mäx Müßtermänn".toByteArray())
    }
}
