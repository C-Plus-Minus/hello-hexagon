package core.component.greet.domain.events

import core.component.greet.domain.Event
import core.component.greet.domain.valueobject.Id

data class RestoredGreetingDisplayed(val id: Id) : Event
