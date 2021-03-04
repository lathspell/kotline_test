package de.lathspell.test

import org.junit.jupiter.api.Test

// https://stackoverflow.com/questions/66468968/kotlin-variables-not-getting-initialized-but-does-when-useless-side-effect-is-e
class StrangeInitTest {

    class A {

        val p = run {
            a = 1
            b = 3
            println("init: $a $b")
        }

        var a = 0
        var b = 2
    }

    @Test
    fun test() {
        val a = A()
        println("main: ${a.a} ${a.b}")
    }

}
