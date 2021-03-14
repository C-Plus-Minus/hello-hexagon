package adapter.driving

import core.component.greet.domain.Event
import core.component.greet.domain.entity.Greeting
import core.component.greet.domain.events.ReadGreetingStored
import core.component.greet.domain.events.RestoredGreetingDisplayed
import core.component.greet.domain.valueobject.Text
import core.component.greet.handler.DisplayGreetingTextHandler
import core.component.greet.handler.ReadGreetingNameHandler
import core.component.greet.usecase.DisplayGreetingText
import core.component.greet.usecase.ReadGreetingName
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
            ReadGreetingName::class.java,
            ReadGreetingNameHandler(textReader, textWriter, greetingRepository)
        )
        commandBus.register(
            DisplayGreetingText::class.java,
            DisplayGreetingTextHandler(textWriter, greetingRepository)
        )
        eventBus.register(this)
    }

    fun getUserInput() {
        commandBus.execute(ReadGreetingName(Text("Whom would you like to greet?")))

        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun handle(event: ReadGreetingStored) {
        log(event)
        commandBus.execute(DisplayGreetingText(event.id))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handle(event: RestoredGreetingDisplayed) {
        log(event)
        log("How to forward any async answer to controller?")
    }

    private fun log(event: Event) {
        println("(Event) $event")
    }

    private fun log(message: String) {
        println("(Debug) $message")
    }
}
