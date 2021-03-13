package core.component.greet.handler.command

import core.component.CommandHandler
import core.component.greet.domain.Command
import core.component.greet.domain.events.GreetingPresented
import core.component.greet.usecase.PresentGreetingText
import adapters.driven.AndroidEventBus
import core.port.driven.TextWriter

class PresentGreetingTextHandler(private val textWriter: TextWriter) : CommandHandler(AndroidEventBus()) {

    override fun handle(command: Command) {
        command as PresentGreetingText
        textWriter.write(command.text)

        eventBus.publish(GreetingPresented())
    }
}
