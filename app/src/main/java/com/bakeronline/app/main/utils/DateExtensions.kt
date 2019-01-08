package com.bakeronline.app.main.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.concurrent.TimeUnit

/**
 * @author Marc Moreno
 * @since 8/1/2019.
 */

internal val calendar: Calendar by lazy {
    getInstance()
}

internal val today: Date by lazy {
    val currentDate = Date()
    Dates.of(currentDate.currentYear, currentDate.monthOfYear, currentDate.dayOfMonth)
}

operator fun Date.plus(duration: Duration): Date {
    calendar.time = this
    calendar.add(duration.unit, duration.value)
    return calendar.time
}

operator fun Date.minus(duration: Duration): Date {
    calendar.time = this
    calendar.add(duration.unit, -duration.value)
    return calendar.time
}

fun Date.differenceInDays(other: Date): Int {
    return (other.time - this.time).fromMillistoDays()
}

fun Date.with(year: Int = -1, month: Int = -1, day: Int = -1, hour: Int = -1, minute: Int = -1, second: Int = -1): Date {
    calendar.time = this
    if (year > -1) calendar.set(YEAR, year)
    if (month > -1) calendar.set(MONTH, month - 1)
    if (day > -1) calendar.set(DATE, day - 1)
    if (hour > -1) calendar.set(HOUR, hour)
    if (minute > -1) calendar.set(MINUTE, minute)
    if (second > -1) calendar.set(SECOND, second)
    return calendar.time
}

fun Date.with(weekday: Int = -1): Date {
    calendar.time = this
    if (weekday > -1) calendar.set(WEEK_OF_MONTH, weekday)
    return calendar.time
}

val Date.minutOfTheHour: Int
    get() = calendar.apply { time = this@minutOfTheHour }.get(MINUTE)

val Date.hourOfTheDay: Int
    get() = calendar.apply { time = this@hourOfTheDay }.get(HOUR_OF_DAY)

val Date.dayOfYeah: Int
    get() = calendar.apply { time = this@dayOfYeah }.get(DAY_OF_YEAR)

val Date.dayOfMonth: Int
    get() = calendar.apply { time = this@dayOfMonth }.get(DAY_OF_MONTH)

val Date.monthOfYear: Int
    get() = calendar.apply { time = this@monthOfYear }.get(MONTH)

val Date.monthOfYearByName: String
    get() = SimpleDateFormat("MMMM", Locale.getDefault()).format(this)

val Date.week: Int
    get() = calendar.apply { time = this@week }.get(WEEK_OF_YEAR)

val Date.currentYear: Int
    get() = calendar.apply { time = this@currentYear }.get(YEAR)

fun Date.appFormatedDateText(format: String = "dd-MM-yyyy",
                             locale: Locale = Locale.UK,
                             timeZone: TimeZone = TimeZone.getTimeZone("UTC")) = SimpleDateFormat(format, locale).apply { setTimeZone(timeZone) }.format(this)

fun String.toDate(format: String = "dd-MM-yyyy", locale: Locale = Locale.GERMANY): Date? {
    return try {
        SimpleDateFormat(format, locale)
                .apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }.parse(this)
    } catch (e: Exception) {
        null
    }
}

infix fun Date.inUnits(timeUnit: TimeUnit): Long = timeUnit.convert(time, TimeUnit.MILLISECONDS)

