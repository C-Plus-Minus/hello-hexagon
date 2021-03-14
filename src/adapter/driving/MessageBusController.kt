package adapter.driving

import core.port.driven.EventBus
import core.port.driving.CommandBus

internal abstract class MessageBusController(
    protected val commandBus: CommandBus,
    protected val eventBus: EventBus
)
