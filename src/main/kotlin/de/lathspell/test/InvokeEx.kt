package de.lathspell.test

class InvokeEx {

    val log: MutableList<String> = mutableListOf()

    operator fun invoke() {
        log.add("invoked")
    }

    init {
        log.add("ctor")
    }

}

