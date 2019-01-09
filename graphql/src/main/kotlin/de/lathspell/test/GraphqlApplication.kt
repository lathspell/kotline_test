package de.lathspell.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraphqlApplication

fun main(args: Array<String>) {
	runApplication<GraphqlApplication>(*args)
}
