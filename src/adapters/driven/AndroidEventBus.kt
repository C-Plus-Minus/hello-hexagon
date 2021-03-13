package adapters.driven

import core.component.greet.domain.Event
import core.port.driven.EventBus


class AndroidEventBus : EventBus {
    override fun register(classContainingHandler: Any) {
        org.greenrobot.eventbus.EventBus.getDefault().register(classContainingHandler)
    }

    override fun unregister(classContainingHandler: Any) {
        org.greenrobot.eventbus.EventBus.getDefault().unregister(classContainingHandler)
    }

    override fun publish(event: Event) {
        org.greenrobot.eventbus.EventBus.getDefault().post(event)
    }
}
