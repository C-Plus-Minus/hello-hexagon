package adapters.driving

import adapters.driven.AndroidEventBus
import adapters.driven.ConsoleTextReader
import adapters.driven.ConsoleTextWriter
import core.component.greet.domain.events.GreetingPresented
import core.component.greet.domain.events.GreetingWasRead
import core.component.greet.domain.valueobject.Text
import core.component.greet.commandhandler.PresentGreetingTextHandler
import core.component.greet.commandhandler.ReadGreetingTextHandler
import core.component.greet.usecase.PresentGreetingText
import core.component.greet.usecase.ReadGreetingText
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

internal class GreetController : MessageBusController(SimpleCommandBus(), AndroidEventBus()) {

    init {
        commandBus.register(
            ReadGreetingText::class.java,
            ReadGreetingTextHandler(ConsoleTextReader(), ConsoleTextWriter())
        )
        commandBus.register(
            PresentGreetingText::class.java,
            PresentGreetingTextHandler(ConsoleTextWriter())
        )
        eventBus.register(this)
    }

    fun getUserInput() {
        commandBus.execute(ReadGreetingText(Text("Whom would you like to greet?")))

        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun handle(event: GreetingWasRead) {
        println("(Event) GreetingWasRead")

        commandBus.execute(PresentGreetingText(Text("${event.subject.value}")))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handle(event: GreetingPresented) {
        println("(Event) GreetingPresented")
        println("(Debug) How to forward any async answer to controller?")
    }
}
