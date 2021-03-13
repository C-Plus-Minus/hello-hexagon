package core.port.driven

import core.component.greet.domain.valueobject.Text

interface TextReader {
    fun read(): Text
}
