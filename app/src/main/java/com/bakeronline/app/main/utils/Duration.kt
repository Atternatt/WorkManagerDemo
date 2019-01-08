package com.bakeronline.app.main.utils

import com.bakeronline.app.main.utils.extensions.calendar
import java.util.*

/**
 * @author Marc Moreno
 * @since 8/1/19.
 */
class Duration(internal val unit: Int, internal val value: Int) {

    val ago = calculate(from = Date(), value = -value)

    val since = calculate(from = Date(), value = value)

    private fun calculate(from: Date, value: Int): Date {
        calendar.time = from
        calendar.add(unit, value)
        return calendar.time
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Duration) {
            return false
        }
        return unit == other.unit && value == other.value
    }

}