plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"
}

application {
    mainClass = "com.oxy.messager.MessagerApplicationKt"
}

dependencies {
    implementation(libs.http4k.client.okhttp)
    implementation(libs.http4k.core)
    implementation(libs.http4k.format.kotlinx.serialization)
    implementation(libs.http4k.server.jetty)
    implementation(libs.kotlin.stdlib.jdk8)
}
