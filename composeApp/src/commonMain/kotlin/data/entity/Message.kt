package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
sealed class Message {
    // never determine its value in client.
    @SerialName("id")
    abstract val id: Long

    @SerialName("content")
    abstract val content: String

    // conversation id
    @SerialName("cid")
    abstract val cid: Long

    @SerialName("type")
    abstract val type: String

    @Serializable
    @SerialName(TYPE_UNSUPPORTED)
    data class Unsupported(
        @SerialName("content")
        override val content: String,
        @SerialName("cid")
        override val cid: Long,
        @SerialName("id")
        override val id: Long = 0L
    ) : Message() {
        @Transient
        override val type: String = TYPE_UNSUPPORTED
    }

    @Serializable
    @SerialName(TYPE_PLAIN_TEXT)
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
        override val type: String = TYPE_PLAIN_TEXT
    }

    companion object {
        const val TYPE_UNSUPPORTED = "unsupported"
        const val TYPE_PLAIN_TEXT = "plain_text"
        val module by lazy {
            SerializersModule {
                polymorphic(Message::class) {
                    subclass(Unsupported::class)
                    subclass(PlainText::class)
                }
            }
        }

        fun plainText(text: String, cid: Long): PlainText = PlainText(text, cid)
    }
}
