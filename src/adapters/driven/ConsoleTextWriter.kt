package adapters.driven

import core.component.greet.domain.valueobject.Text
import core.port.driven.TextWriter

class ConsoleTextWriter : TextWriter {
    override fun write(text: Text) {
        println(text.value)
    }
}
