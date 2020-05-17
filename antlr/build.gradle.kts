import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion

    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"

    antlr
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2:1.4.199")

    antlr("org.antlr:antlr4:4.8-1")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.assertj:assertj-core:3.11.1")
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

tasks.withType<Test> {
    useJUnitPlatform()
}
