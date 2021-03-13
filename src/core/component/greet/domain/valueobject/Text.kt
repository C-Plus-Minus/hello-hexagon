package core.component.greet.domain.valueobject

data class Text(val value: String?) {
    init {
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException("Displayed text must not be empty")
        }
    }
}
