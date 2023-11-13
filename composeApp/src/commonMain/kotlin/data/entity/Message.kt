package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
sealed class Message {
    @SerialName("id")
    abstract val id: Long

    @SerialName("content")
    abstract val content: String

    @SerialName("cid")
    abstract val cid: Long

    @SerialName("type")
    abstract val type: Int

    @Serializable
    @SerialName("unsupported")
    data class Unsupported(
        @SerialName("content")
        override val content: String,
        @SerialName("cid")
        override val cid: Long,
        @SerialName("id")
        override val id: Long = 0L
    ) : Message() {
        @Transient
        override val type: Int = TYPE_UNSUPPORTED
    }

    @Serializable
    @SerialName("plain_text")
    data class PlainText(
        @SerialName("text")
        val text: String,
        @SerialName("cid")
        override val cid: Long,
        @SerialName("id")
        override val id: Long = 0L,
    ) : Message() {
        @Transient
        override val content: String = text

        @Transient
        override val type: Int = TYPE_PLAIN_TEXT
    }

    companion object {
        const val TYPE_UNSUPPORTED = -1
        const val TYPE_PLAIN_TEXT = 0
        private val module by lazy {
            SerializersModule {
                polymorphic(Message::class) {
                    subclass(Unsupported::class)
                    subclass(PlainText::class)
                }
            }
        }
        val json by lazy {
            Json {
                encodeDefaults = true
                serializersModule = module
            }
        }
    }
}
