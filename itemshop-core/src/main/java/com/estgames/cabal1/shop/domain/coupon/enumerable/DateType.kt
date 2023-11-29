package com.estgames.cabal1.shop.domain.coupon.enumerable

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

enum class DateType(val message: String) {
    IMMEDIATELY("즉시") {
        override fun date(value: String?): Date = Date(System.currentTimeMillis())
    },
    ADD_DAYS("설정일 후 사용") {
        override fun date(value: String?): Date {
            if (value == null) throw IllegalArgumentException("value null error: ADD_DAYS")
            val cal = Calendar.getInstance()
            cal.time = Date(System.currentTimeMillis())
            cal.add(Calendar.DATE, value.toInt())
            return cal.time
        }
    },
    FIX_DATE("날짜 고정(yyyy-MM-dd HH:mm:ss)") {
        override fun date(value: String?): Date {
            if (value == null) throw IllegalArgumentException("value null error: ADD_DAYS")
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try {
                return format.parse(value) ?: throw ParseException("Date parse error - $value", 0)
            } catch (e: ParseException) {
                throw IllegalArgumentException("Date format error - $value")
            }
        }
    },
    UNLIMITED("제한 없음") {
        override fun date(value: String?): Date? = null
    };

    abstract fun date(value: String?): Date?
}