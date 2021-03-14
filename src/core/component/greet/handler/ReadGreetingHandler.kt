package core.component.greet.handler

import core.component.CommandHandler
import core.component.greet.domain.Command
import core.component.greet.domain.events.ReadGreetingStored
import core.component.greet.domain.valueobject.Text
import core.component.greet.usecase.ReadGreetingText
import adapter.driven.AndroidEventBus
import core.component.greet.domain.entity.Greeting
import core.port.driven.Repository
import core.port.driven.TextReader
import core.port.driven.TextWriter

class ReadGreetingHandler(
    private val reader: TextReader,
    private val writer: TextWriter,
    private val greetingRepository: Repository<Greeting>
) : CommandHandler(
    AndroidEventBus()
) {
    override fun handle(command: Command) {
        command as ReadGreetingText

        val consolePrompt = Text(command.prompt.value)
        writer.write(consolePrompt)
        val subject = reader.read()

        val greeting = Greeting()
        greeting.setSubject(subject)

        val id = greetingRepository.save(greeting)
        eventBus.publish(ReadGreetingStored(id))
    }
}
