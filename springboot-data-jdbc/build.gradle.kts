import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    kotlin("plugin.jpa") version "1.4.30"

    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.springframework.boot") version "2.4.4"
}

group = "de.lathspell.test"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    // JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.flywaydb:flyway-core")
    testRuntimeOnly("com.h2database:h2")
    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(SKIPPED, STARTED, PASSED, FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true // print all stdout/stderr output to console
        minGranularity = 0 // show class and method names
    }
}
