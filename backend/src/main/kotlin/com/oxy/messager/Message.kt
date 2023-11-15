package com.oxy.messager

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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
    abstract val type: String

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

        override fun withId(id: Long): PlainText {
            return copy(id = id)
        }
    }

    companion object {
        const val TYPE_PLAIN_TEXT = "plain_text"

        val module by lazy {
            SerializersModule {
                polymorphic(Message::class) {
                    subclass(PlainText::class)
                }
            }
        }
    }

    // server only
    abstract fun withId(id: Long): Message
}
