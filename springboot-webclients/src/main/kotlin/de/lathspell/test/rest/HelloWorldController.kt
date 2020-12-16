package de.lathspell.test.rest

import de.lathspell.test.rest.model.Greeting
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/hello-world/txt", produces = [TEXT_PLAIN_VALUE])
    fun helloWorldAsText() = "Hello World"

    @GetMapping("/hello-world/json")
    fun helloWorldAsJson() = Greeting("Hello", "World")

}