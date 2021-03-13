package core.port.driving

import core.component.CommandHandler
import core.component.greet.domain.Command

interface CommandBus {
    fun <T : Command>register(clazz: Class<T>, handler: CommandHandler)
    fun execute(command: Command)
}
