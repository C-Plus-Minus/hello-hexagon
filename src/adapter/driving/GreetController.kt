package adapter.driving

import core.component.greet.domain.entity.Greeting
import core.component.greet.domain.events.ReadGreetingStored
import core.component.greet.domain.events.RestoredGreetingDisplayed
import core.component.greet.domain.valueobject.Text
import core.component.greet.handler.DisplayGreetingHandler
import core.component.greet.handler.ReadGreetingHandler
import core.component.greet.usecase.DisplayGreetingText
import core.component.greet.usecase.ReadGreetingText
import core.port.driven.EventBus
import core.port.driven.Repository
import core.port.driven.TextReader
import core.port.driven.TextWriter
import core.port.driving.CommandBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

internal class GreetController(
    textReader: TextReader,
    textWriter: TextWriter,
    greetingRepository: Repository<Greeting>,
    commandBus: CommandBus,
    eventBus: EventBus
) : MessageBusController(
    commandBus,
    eventBus
) {
    init {
        commandBus.register(
            ReadGreetingText::class.java,
            ReadGreetingHandler(textReader, textWriter, greetingRepository)
        )
        commandBus.register(
            DisplayGreetingText::class.java,
            DisplayGreetingHandler(textWriter, greetingRepository)
        )
        eventBus.register(this)
    }

    fun getUserInput() {
        commandBus.execute(ReadGreetingText(Text("Whom would you like to greet?")))

        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun handle(event: ReadGreetingStored) {
        println("(Event) $event")
        commandBus.execute(DisplayGreetingText(event.id))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handle(event: RestoredGreetingDisplayed) {
        println("(Event) $event")
        println("(Debug) How to forward any async answer to controller?")
    }
}