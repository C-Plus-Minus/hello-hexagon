package core.component.greet.handler.command

import core.component.CommandHandler
import core.component.greet.domain.Command
import core.component.greet.domain.events.GreetingWasRead
import core.component.greet.domain.valueobject.Text
import core.component.greet.usecase.ReadGreetingText
import adapters.driven.AndroidEventBus
import core.port.driven.TextReader
import core.port.driven.TextWriter

class ReadGreetingTextHandler(
    private val reader: TextReader,
    private val writer: TextWriter
) : CommandHandler(
    AndroidEventBus()
) {
    override fun handle(command: Command) {
        command as ReadGreetingText

        val consolePrompt = Text(command.prompt.value)
        writer.write(consolePrompt)
        val greeting = reader.read()

        eventBus.publish(GreetingWasRead(greeting))
    }
}
