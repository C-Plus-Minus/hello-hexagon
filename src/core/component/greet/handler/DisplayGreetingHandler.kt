package core.component.greet.handler

import adapter.driven.AndroidEventBus
import core.component.CommandHandler
import core.component.greet.domain.Command
import core.component.greet.domain.entity.Greeting
import core.component.greet.domain.events.RestoredGreetingDisplayed
import core.component.greet.usecase.DisplayGreetingText
import core.port.driven.Repository
import core.port.driven.TextWriter

class DisplayGreetingHandler(
    private val textWriter: TextWriter,
    private val greetingRepository: Repository<Greeting>
) : CommandHandler(
    AndroidEventBus()
) {
    override fun handle(command: Command) {
        command as DisplayGreetingText

        val greeting = greetingRepository.findById(command.id)
        textWriter.write(greeting.compose())

        eventBus.publish(RestoredGreetingDisplayed(command.id))
    }
}
