package com.m2f.app.main.utils

import com.m2f.app.main.utils.extensions.calendar
import java.util.*

/**
 * @author Marc Moreno
 */
object Dates {

    val now = Date()

    val today = Date()

    val tomorrow = setDate(value = 1)

    val yesterday = setDate(value = -1)

    private fun setDate(value: Int): Date {
        calendar.time = Date()
        calendar.add(Calendar.DATE, value)
        return calendar.time
    }

    fun of(year: Int = -1, month: Int = -1, day: Int = -1, hour: Int = -1, minute: Int = -1, second: Int = -1): Date {
        calendar.time = Date()
        if (year > -1) calendar.set(Calendar.YEAR, year)
        if (month > -1) calendar.set(Calendar.MONTH, month)
        if (day > -1) calendar.set(Calendar.DATE, day)
        if (hour > -1) calendar.set(Calendar.HOUR, hour)
        if (minute > -1) calendar.set(Calendar.MINUTE, minute)
        if (second > -1) calendar.set(Calendar.SECOND, second)
        return calendar.time
    }

}