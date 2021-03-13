package core.component.greet.domain.events

import core.component.greet.domain.Event
import core.component.greet.domain.valueobject.Text

data class GreetingWasRead(val greeting: Text?) : Event
