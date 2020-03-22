package msat.model

import java.util.*
import kotlin.random.Random

class Person {

    enum class StatusType {
        SICK, CURED, DEAD, NOT_AFFECTED
    }

    private val name = "Magno"
    private val birthday = Calendar.getInstance()

    val status = StatusType.values()[Random.nextInt(0, StatusType.values().size)]

    override fun toString(): String {
        return "Person:{ " +
                "name: $name, " +
                "status: $status" +
                "}"
    }

}