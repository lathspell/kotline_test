
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.10"
    kotlin("jvm") version kotlinVersion

    id("com.github.ben-manes.versions") version "0.33.0"        // https://github.com/ben-manes/gradle-versions-plugin for ":dependencyUpdates"
}

group = "de.lathspell"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    // JSON
    val jacksonVersion = "2.9.6"
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // Test
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    outputs.upToDateWhen { false } // Tests immer ausführen!
    testLogging {
        events(SKIPPED, STARTED, PASSED, FAILED)
    }
    testLogging.exceptionFormat = TestExceptionFormat.FULL
    testLogging.showStandardStreams = true // print all stdout/stderr output to console
    testLogging.minGranularity = 0 // show class and method names

}
