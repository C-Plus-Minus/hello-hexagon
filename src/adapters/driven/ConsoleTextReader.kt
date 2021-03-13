package adapters.driven

import core.component.greet.domain.valueobject.Text
import core.port.driven.TextReader

class ConsoleTextReader : TextReader {
    override fun read(): Text {
        return Text(readLine())
    }

}
