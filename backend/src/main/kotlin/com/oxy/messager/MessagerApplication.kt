package com.oxy.messager

import com.oxy.messager.entity.Message
import com.oxy.messager.ktx.format
import com.oxy.messager.wrapper.Backend
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
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
    "/chats/sendMessage" bind Method.POST to { request ->
        try {
            val message = request.format<Message>()
            println("before: $message")
            // save to db then return
            val result = json.encodeToString(Backend.success(message testWithId 1L))
            println("after: $result")
            Response(OK).body(result)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            throw e
        }
    }
)

fun main() {
    val printingApp: HttpHandler = PrintRequest().then(app)
    val server = printingApp.asServer(Jetty(9000)).start()
    println("Server started on " + server.port())

}
