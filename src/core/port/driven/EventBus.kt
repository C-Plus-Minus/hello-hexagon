package core.port.driven

import core.component.greet.domain.Event


interface EventBus {
    fun register(classContainingHandler: Any)
    fun unregister(classContainingHandler: Any)
    fun publish(event: Event)
}
