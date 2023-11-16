import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import data.api.ChatApi
import data.json
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit

val kodein = DI {
    bindSingleton<Json> { json }
    bindSingleton<Retrofit> {
        val contentType = MediaType.get("application/json")
        val json: Json by di.instance()
        Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
    bindSingleton<ChatApi> {
        val retrofit: Retrofit by di.instance()
        retrofit.create(ChatApi::class.java)
    }
}