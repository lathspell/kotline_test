package de.lathspell.test.cli.linux

import kotlin.system.*

import de.lathspell.test.calc.Calc
import de.lathspell.test.filesystem.FileSystem
import de.lathspell.test.helloworld.HelloWorld

fun main(args: Array<String>) {
    require(args.size >= 1) { "Usage: native.kexe <command> <options>" }
    when (args[0]) {
        "hello-world" -> HelloWorld().run(args)
        "calc" -> Calc().run(args)
        "fs" -> FileSystem().run(args)
        else -> {
            println("Unknown command '${args[0]}'!")
            exitProcess(1)
        }
    }
}
