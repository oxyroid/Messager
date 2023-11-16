package com.oxy.messager

import com.oxy.messager.entity.Message
import com.oxy.messager.wrapper.Backend
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "127.0.0.1",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun Application.configureRouting() {
    routing {
        route("/chats") {
            post("sendMessage") {
                try {
                    val message = call.receive<Message>()
                    println("before: $message")
                    // save to db then return
                    val result = Backend.success(message testWithId 1L)
                    println("after: $result")
                    call.respond(result)
                } catch (e: Exception) {
                    println(e.stackTraceToString())
                    throw e
                }
            }
        }
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            json = Json {
                encodeDefaults = true
                serializersModule = Message.module
                prettyPrint = true
            }
        )
    }
}