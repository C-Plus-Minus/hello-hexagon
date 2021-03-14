package core.port.driven

import core.component.greet.domain.valueobject.Id

interface Repository<T> {
    fun save(entity: T): Id
    fun findById(id: Id): T
}
