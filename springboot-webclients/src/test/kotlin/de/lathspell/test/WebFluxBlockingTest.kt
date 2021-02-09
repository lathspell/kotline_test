package de.lathspell.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.reactive.function.client.toEntity

/** Using WebClient for simple blocking requests. */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebFluxBlockingTest(@LocalServerPort port: Int) {

    private val baseUri = "http://localhost:$port/hello-world"
    private val client = WebClient.create(baseUri)

    /** With toEntity() we get the response entity that contains the body as well as the status code and headers. */
    @Test
    fun `get text with response entity`() {
        val response = client.get().uri("/txt").accept(TEXT_PLAIN).retrieve()
            .toEntity<String>()
            .block()!!

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(response.body).isEqualTo("Hello World")
    }

    /** With bodyToMono() we only get the response body as the specified class. With block() it will wait for the response. */
    @Test
    fun `get text as string`() {
        val actual = client.get().uri("/txt").accept(TEXT_PLAIN)
            .retrieve()
            .bodyToMono<String>()
            .block()!!

        assertThat(actual).isEqualTo("Hello World")
    }

}
