import adapter.driven.AndroidEventBus
import adapter.driven.ConsoleTextReader
import adapter.driven.ConsoleTextWriter
import adapter.driving.GreetController
import adapter.driven.GreetingRepository
import adapter.driving.SimpleCommandBus

fun main() {
    GreetController(
        ConsoleTextReader(),
        ConsoleTextWriter(),
        GreetingRepository(),
        SimpleCommandBus(),
        AndroidEventBus()
    ).getUserInput()
}

