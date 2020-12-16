package de.lathspell.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.TEXT_PLAIN

import kotlinx.coroutines.*
import org.springframework.web.reactive.function.client.*
import reactor.kotlin.core.publisher.toMono

@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebFluxTest(
    @LocalServerPort port: Int
) {

    private val baseUri = "http://localhost:$port/hello-world"

    @Test
    fun `get text with response`() {
        val client = WebClient.create(baseUri)
        val response = client.get().uri("/txt").accept(TEXT_PLAIN).retrieve().toEntity<String>().block()!!
        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(response.body).isEqualTo("Hello World")
    }


    @Test
    fun `get text as Mono`() {
        val client = WebClient.create(baseUri)
        val actual = client.get().uri("/txt").accept(TEXT_PLAIN)
            .retrieve()
            .bodyToMono<String>()
            .block()!!
        assertThat(actual).isEqualTo("Hello World")
    }

    @Test
    fun `get text with async`() {
        val client = WebClient.create(baseUri)

        runBlocking {
            val actual = async {
                client.get().uri("/txt").accept(TEXT_PLAIN)
                    .retrieve()
                    .awaitBody<String>()
            }

            assertThat(actual.await()).isEqualTo("Hello World")
        }
    }

    @Test
    fun `get text with Mono`() {
        GlobalScope.async {
            val actual = txtUsingAwaitBody()
            assertThat(actual).isEqualTo("Hello World")
        }
    }

    private suspend fun txtUsingAwaitBody(): String {
        val client = WebClient.create(baseUri)
        return client.get().uri("/txt").accept(TEXT_PLAIN)
            .retrieve()
            .awaitBody() // can only be used in a coroutine context like async/suspend
    }
}
