package msat.view.converter

import msat.model.Person
import msat.view.component.Ball
import java.awt.Color
import java.awt.Component

fun Person.drawAsBall(person: Person, maxWidth: Int, maxHeight: Int): Component {
    val color = when (person.status) {
        Person.StatusType.SICK -> Color.RED
        Person.StatusType.CURED -> Color.GREEN
        Person.StatusType.NOT_AFFECTED -> Color.YELLOW
        Person.StatusType.DEAD -> Color.BLACK
    }
    return Ball(color, maxWidth, maxHeight)
}

