package com.oxy.messager.wrapper

import kotlinx.serialization.Serializable

// server
@Serializable
data class Backend<T>(
    val code: Int = 200,
    val data: T? = null,
    val message: String = "success"
) {
    companion object {
        fun <T> success(data: T): Backend<T> = Backend(data = data)
        fun <T> failure(message: String, code: Int): Backend<T> = Backend(message = message, code = code)
    }
}
