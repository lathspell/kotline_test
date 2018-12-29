package de.lathspell.test.filesystem

import platform.posix.*
import kotlinx.cinterop.CPointer

class FileSystem {

    fun run(args: Array<String>) {
        when (args[1]) {
            "ls" -> ls(args[2])
            else -> throw IllegalArgumentException("Unknown sub command!")
        }
    }

    // Usage: native.exe fs ls .
    fun ls(dirName: String) {
        val dir = opendir(dirName)
        if (dir == null) {
            perror("fopen failed")
            return
        }

        var entry = readdir(dir)
        while (entry != null) {
            println(entry)
            entry = readdir(dir)
        }

        closedir(dir)
    }
}