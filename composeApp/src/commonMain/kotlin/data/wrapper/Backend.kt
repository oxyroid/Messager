package data.wrapper

import kotlinx.serialization.Serializable

// client
@Serializable
data class Backend<T>(
    val code: Int = 200,
    val data: T? = null,
    val message: String = ""
) : Castable {
    override fun cast(): CastableException = castable(data?.toString().orEmpty()) {
        add("code" to code)
        add("message" to message)
    }
}

val Backend<*>.isSuccessful: Boolean get() = code == 200
fun <T> Backend<T>.getOrThrow(): T {
    return checkNotNull(data) { "data is null!" }
}
