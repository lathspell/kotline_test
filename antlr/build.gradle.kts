import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion
    antlr
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.assertj:assertj-core:3.11.1")
    antlr("org.antlr:antlr4:4.8-1")
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}
