package de.lathspell.test

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText(text = "Hello World!", contentType = ContentType.Text.Plain /* default */)
            }
            get("/hello/{name}") {
                call.respondText(text = "Hello " + call.parameters["name"])
            }
        }
    }
    server.start(wait = true)
}
