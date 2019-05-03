import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    application
    id("com.bmuschko.docker-java-application") version "4.8.0"   // https://bmuschko.github.io/gradle-docker-plugin for ":dockerBuildImage"
    id("com.github.ben-manes.versions") version "0.21.0"         // https://github.com/ben-manes/gradle-versions-plugin for ":dependencyUpdates"
}

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "1.1.4"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-server-jetty:$ktorVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

application {
    mainClassName = "de.lathspell.test.AppKt"
}

docker {
    javaApplication {
        baseImage.set("openjdk:8-jdk-alpine")
        maintainer.set("cb@lathspell.de")
        ports.add(8080)
        tag.set("lathspell/ktor_docker:latest")
    }
}