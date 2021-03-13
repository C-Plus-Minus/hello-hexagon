package core.component.greet.usecase

import core.component.greet.domain.Command
import core.component.greet.domain.valueobject.Text

data class ReadGreetingText(val prompt: Text) : Command
