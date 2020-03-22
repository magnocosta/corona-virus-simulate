package msat.infra

import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val tag = "MSAT"

    fun debug(text: String) {
        log("debug", text)
    }

    fun info(text: String) {
        log("info", text)
    }

    fun warn(text: String) {
        log("warn", text)
    }

    fun error(text: String) {
        log("error", text)
    }

    private fun log(level: String, text: String) {
        println(transformByTemplate(level, text))
    }

    private fun transformByTemplate(level: String, text: String): String {
        return "${moment()} - [$tag] - [$level]: $text"
    }

    private fun moment(): String {
        val timeNow = GregorianCalendar.getInstance().time
        return SimpleDateFormat("dd/mm/yyyy HH:MM:SS").format(timeNow)
    }

}