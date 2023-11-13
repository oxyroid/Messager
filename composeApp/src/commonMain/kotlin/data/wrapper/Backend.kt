package data.wrapper

import kotlinx.serialization.Serializable

@Serializable
sealed class Backend<T> {
    data class Successed<T>(
        val data: T
    ): Backend<T>()
    data class Failured(
        val message: String,
        val code: Int
    ): Backend<Nothing>()
}

fun <T> Backend<T>.getOrNull(): T? {
    return if (this is Backend.Successed<T>) data
    else null
}