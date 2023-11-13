package data.api

import data.entity.Message
import data.wrapper.Backend
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {
    @POST("/chats/sendMessage")
    suspend fun sendMessage(@Body message: Message): Backend<Message>
}