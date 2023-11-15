package com.oxy.messager

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.filter.inIntelliJOnly
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

val json by lazy {
    Json {
        encodeDefaults = true
        serializersModule = Message.module
        classDiscriminator = "type"
        prettyPrint = true
    }
}

val app: HttpHandler = routes(
    "/chats/sendMessage" bind Method.POST to {
        try {
            val body = it.bodyString()
            println("body: $body")
            val message = json.decodeFromString<Message>(body)
            val result = json.encodeToString(Backend.success(message.withId(1)))
            println("result: $result")
            Response(OK).body(result)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            throw e
        }
    }
)

fun main() {
    val printingApp: HttpHandler = PrintRequest().inIntelliJOnly().then(app)
    val server = printingApp.asServer(Jetty(9000)).start()
    println("Server started on " + server.port())

}
