package core.component.greet.domain.events

import core.component.greet.domain.Event
import core.component.greet.domain.valueobject.Id

data class ReadGreetingStored(val id: Id) : Event
