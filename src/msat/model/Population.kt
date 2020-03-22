package msat.model

import java.util.*

class Population(size: Int) {

    val people = Collections.unmodifiableList(MutableList(size) { Person() })
    val sickPeople = people.filter { it.status == Person.StatusType.SICK }
    val curedPeople = people.filter { it.status == Person.StatusType.CURED }
    val notAffectedPeople = people.filter { it.status == Person.StatusType.NOT_AFFECTED }
    val deadPeople = people.filter { it.status == Person.StatusType.DEAD }

    override fun toString(): String {
        return "Population:{ " +
                "total: ${people.size}, " +
                "sick: ${sickPeople.size}, " +
                "cured: ${curedPeople.size}, " +
                "not_affected: ${notAffectedPeople.size}, " +
                "dead: ${deadPeople.size} " +
                "}"
    }

}