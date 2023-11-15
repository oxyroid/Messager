package feature.settings

import LocalSentry
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cast
import data.api.ChatApi
import data.entity.Message
import kodein
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.withDI
import org.kodein.di.instance
import ui.LocalSpacing

@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier
) = withDI(kodein) {
    val scope = rememberCoroutineScope()
    val spacing = LocalSpacing.current
    val sentry = LocalSentry.current
    val di = localDI()
    val api by di.instance<ChatApi>()
    Column(
        modifier = modifier.padding(spacing.medium),
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var url by remember { mutableStateOf("") }
        TextField(
            value = url,
            onValueChange = { url = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                    try {
                        val message = Message.plainText("Hello, World", 1L)
                        val backend = api.sendMessage(message)
                        sentry.cast(backend)
                    } catch (e: Exception) {
                        sentry.catch(e)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SEND")
        }
    }
}