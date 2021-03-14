package adapter.driven

import core.component.greet.domain.entity.Greeting
import core.component.greet.domain.valueobject.Id
import core.port.driven.Repository
import java.lang.RuntimeException

class GreetingRepository : Repository<Greeting> {

    private var idCounter = 0L
    private val entities = mutableMapOf<Long, Greeting>()

    override fun save(entity: Greeting): Id {
        entities.keys.firstOrNull { entities[it] == entity }.let {
            val id = it ?: ++idCounter
            entities[id] = entity
            return Id(id)
        }
    }

    override fun findById(id: Id): Greeting {
        return entities.getOrElse(id.value) {
            throw RuntimeException("No greeting with ID {${id.value}} exists.")
        }
    }
}
