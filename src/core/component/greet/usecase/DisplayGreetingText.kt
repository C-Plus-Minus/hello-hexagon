package core.component.greet.usecase

import core.component.greet.domain.Command
import core.component.greet.domain.valueobject.Id

data class DisplayGreetingText(val id: Id) : Command
