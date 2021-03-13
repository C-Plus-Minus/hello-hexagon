package core.component

import core.component.greet.domain.Command
import core.port.driven.EventBus

abstract class CommandHandler(protected val eventBus: EventBus) {
    abstract fun handle(command: Command)
}
