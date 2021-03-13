package core.component.greet.commandhandler

import core.component.CommandHandler
import core.component.greet.domain.Command
import core.component.greet.domain.events.GreetingPresented
import core.component.greet.usecase.PresentGreetingText
import adapters.driven.AndroidEventBus
import core.component.greet.domain.entity.Greeting
import core.port.driven.TextWriter

class PresentGreetingTextHandler(private val textWriter: TextWriter) : CommandHandler(AndroidEventBus()) {

    override fun handle(command: Command) {
        command as PresentGreetingText

        val greeting = Greeting()
        greeting.setSubject(command.subject)
        textWriter.write(greeting.compose())

        eventBus.publish(GreetingPresented())
    }
}
