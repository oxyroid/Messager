package data

import data.entity.Message
import kotlinx.serialization.json.Json

// client
val json by lazy {
    Json {
        encodeDefaults = true
        serializersModule = Message.module
        classDiscriminator = "type"
    }
}
