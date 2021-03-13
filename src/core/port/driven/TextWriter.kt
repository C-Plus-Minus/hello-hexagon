package core.port.driven

import core.component.greet.domain.valueobject.Text

interface TextWriter {
    fun write(text: Text)
}
