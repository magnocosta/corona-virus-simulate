package msat.model

import java.util.*
import kotlin.math.floor

class Population(size: Int) {

    val people: List<Person> = Collections.unmodifiableList(MutableList(size) { Person() })

    private val sickPeople = people.filter { it.status == Person.StatusType.SICK }
    private val curedPeople = people.filter { it.status == Person.StatusType.CURED }
    private val notAffectedPeople = people.filter { it.status == Person.StatusType.NOT_AFFECTED }
    private val deadPeople = people.filter { it.status == Person.StatusType.DEAD }

    fun sickPeopleInPercentage(): Double {
        return floor((sickPeople.size.toDouble() / people.size.toDouble()) * 100)
    }

    fun curedPeopleInPercentage(): Double {
        return floor((curedPeople.size.toDouble() / people.size.toDouble()) * 100)
    }

    fun notAffectedPeopleInPercentage(): Double {
        return floor((notAffectedPeople.size.toDouble() / people.size.toDouble()) * 100)
    }

    fun deadPeopleInPercentage(): Double {
        return floor((deadPeople.size.toDouble() / people.size.toDouble()) * 100)
    }

    override fun toString(): String {
        return "Population:{ " +
                "total: ${people.size}, " +
                "sick: ${sickPeople.size}, " +
                "cured: ${curedPeople.size}, " +
                "not_affected: ${notAffectedPeople.size}, " +
                "dead: ${deadPeople.size}" +
                "}"
    }

}