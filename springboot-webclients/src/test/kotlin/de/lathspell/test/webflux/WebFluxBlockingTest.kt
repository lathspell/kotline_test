package de.lathspell.test.webflux

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.reactive.function.client.toEntity

/** Using WebClient for simple blocking requests. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebFluxBlockingTest(@LocalServerPort port: Int) {

    private val baseUri = "http://localhost:$port/hello-world"
    private val client = WebClient.create(baseUri)

    /** With toEntity() we get the response entity that contains the body as well as the status code and headers. */
    @Test
    fun `get text with response entity`() {
        val response = client.get().uri("/txt").accept(MediaType.TEXT_PLAIN).retrieve()
            .toEntity<String>()
            .block()!!

        Assertions.assertThat(response.statusCodeValue).isEqualTo(200)
        Assertions.assertThat(response.body).isEqualTo("Hello World")
    }

    /** With bodyToMono() we only get the response body as the specified class. With block() it will wait for the response. */
    @Test
    fun `get text as string`() {
        val actual = client.get().uri("/txt").accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .bodyToMono<String>()
            .block()!!

        Assertions.assertThat(actual).isEqualTo("Hello World")
    }

}
