import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.databaseModule
import org.koin.core.context.startKoin
import ui.PersonListScreen


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        startKoin {
            modules(databaseModule)
        }
        PersonListScreen()
    }
}
