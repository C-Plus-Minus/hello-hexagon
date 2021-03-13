package core.component.greet.domain.entity

import core.component.greet.domain.valueobject.Text
import java.lang.IllegalStateException

class Greeting {
    private val greetingFormula = "Hello"
    private lateinit var subject: String

    fun setSubject(whom: Text) {
        subject = whom.value!!
    }

    fun compose(): Text {
        if (subject.isInvalid()) throw IllegalStateException("The greeting needs a valid subject")
        return Text("$greetingFormula $subject")
    }

    private fun String.isInvalid() = this.isBlank() || this.length < 2
}


