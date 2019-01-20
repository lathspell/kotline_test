package de.lathspell.test

import de.lathspell.test.model.Person
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import io.ktor.http.content.resource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.http.withCharset
import io.ktor.jackson.jackson
import io.ktor.request.host
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main(args: Array<String>) {
    val server = embeddedServer(factory = Jetty, environment = commandLineEnvironment(args))
    server.start(wait = true)
}


fun Application.main() {
    install(CallLogging) {
        mdc("ip") {
            it.request.origin.remoteHost
        }
        callIdMdc("X-CallId")
    }
    install(ContentNegotiation) {
        jackson()
    }
    install(Compression)
    install(ConditionalHeaders)
    install(CallId) {
        generate(10)
    }
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(TextContent("Sorry, ${it.value} ${it.description}", ContentType.Text.Plain.withCharset(Charsets.UTF_8), it))
        }
    }

    routing {
        trace {
            application.log.info(it.buildText())
        }

        myRoutes()
    }
}

fun Route.myRoutes() {

    static() {
        resource("/", "static/index.html")
    }
    static("/css") {
        resources("static/css")
    }

    get("/hello/{name}") {
        call.respondText(text = "Hello " + call.parameters["name"])
    }

    get("/person/{name}") {
        call.respond(Person(name = call.parameters["name"]!!))
    }

    get("/teapot") {
        call.response.status(HttpStatusCode(418, "I'm a teapot!"))
    }
}
