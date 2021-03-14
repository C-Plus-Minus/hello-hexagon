package adapter.driving

import core.component.CommandHandler
import core.component.greet.domain.Command
import core.port.driving.CommandBus

class SimpleCommandBus : CommandBus {

    private val handlers = mutableMapOf<Class<*>, CommandHandler>()

    override fun <T : Command> register(clazz: Class<T>, handler: CommandHandler) {
        handlers.putIfAbsent(clazz, handler)
    }

    override fun execute(command: Command) {
        handlers.getOrElse(command.javaClass) {
            throw RuntimeException("Unregistered command: $command")
        }.handle(command)
    }
}
