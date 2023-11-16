plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"
    id("io.ktor.plugin") version "2.3.6"
}

application {
    mainClass = "com.oxy.messager.MessagerApplicationKt"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.ktor.server.status.pages.jvm)
    implementation(libs.ktor.server.default.headers.jvm)
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation(libs.logback.classic)
}
