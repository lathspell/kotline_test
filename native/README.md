Gradle Build Plugins
====================

* org.jetbrains.kotlin.konan - original plugin for Kotlin Native (nicknamed "Konan")

* org.jetbrains.kotlin.native - experimental plugin for Kotlin Native that aims to be closed to Kotlin/JVM

* org.jetbrains.kotlin.platform.native - experimental plugin based on Gradle Kotlin Native but for building for more that one OS
    Seems recommended by https://kotlinlang.org/docs/reference/native/gradle_plugin.html

* org.jetbrains.kotlin.multiplatform 1.3.11 - experimental plugin for Multiplatform Projects
    Seems recommended by https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html 

General Caveats:
* No Gradle 5 due to https://github.com/JetBrains/kotlin-native/issues/2440
* No Gradle Kotlin DSL due to lack of support for Gradle 5

Examples
========

As I could not figure out how to create multiple binaries, I only have one LinuxCli which dispatches
to other classes.

    ./gradlew  :compileDebugLinux_x64KotlinNative

    ./build/exe/main/debug/linux_x64/native.kexe hello-world
        Hello World!

    ./build/exe/main/debug/linux_x64/native.kexe calc 3 + 4
        3 + 4 = 7
    
    ./build/exe/main/debug/linux_x64/native.kexe fs ls .
        CPointer(raw=0x932bf0)
        CPointer(raw=0x932c08)
        CPointer(raw=0x932c20)
        ... (so readdir() is pretty useless)
  
Conclusion
==========

* The amount of Gradle plugins is confusing
* Gradle 5 and Kotlin DSL buggy
* No JDK classes like java.util.* available and Posix stuff very basic and badly documented
* IntelliJ gave up on auto-completion, probably due to strange source dir configuration
* Building takes 5s for only one debug binary
