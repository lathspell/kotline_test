package de.lathspell.test.calc

class Calc {
    fun run(args: Array<String>) {
        require(args.size == 4) { "Need 3 parameters!" }

        // args[0] is "calc"
        val a = args[1].toInt()
        val op = args[2]
        val b = args[3].toInt()

        val result = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> throw IllegalArgumentException("Unknown operator '$op'!")
        }

        println("$a $op $b = $result")
    }
}