import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"

    id("com.github.ben-manes.versions") version "0.33.0"        // https://github.com/ben-manes/gradle-versions-plugin for ":dependencyUpdates"
}

group = "de.lathspell"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
}

dependencies {
    // Kotlin
    implementation(kotlin("reflect"))

    // JSON
    val jacksonVersion = "2.9.6"
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // JSON Patch
    implementation("com.flipkart.zjsonpatch:zjsonpatch:0.4.11")

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
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true // print all stdout/stderr output to console
        minGranularity = 0 // show class and method names
    }
}
