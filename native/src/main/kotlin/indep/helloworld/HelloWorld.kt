package de.lathspell.test.helloworld

class HelloWorld {
    fun run(args: Array<String>) {
        require(args.size == 1)
        println("Hello World!")
    }
}