package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.proudct.enumerable.LimitType
import com.estgames.commons.utils.DateFormatUtils
import jakarta.persistence.*
import java.util.*

@Entity
class LimitPolicy(
    @Column
    var startDate: String?,
    @Column
    var finishDate: String?,
    @Column
    @Enumerated(EnumType.STRING)
    var limitType: LimitType,
    @Column
    var value: String?,
    @Column
    var productId: Long?,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val limitPolicyId: Long = 0


    fun isAllowQuantityLimit(count: Int): Boolean {
        return (value?.toIntOrNull() ?: 0) >= count
    }

    fun isAllowDateLimit(): Boolean {
        val currentDateTime = Date()

        if (!startDate.isNullOrBlank()) {
            val startDateTime = DateFormatUtils().getDate(startDate, "yyyy-MM-dd HH:mm:ss")
            if (startDateTime.after(currentDateTime)) {
                return false
            }
        }
        if (!finishDate.isNullOrBlank()) {
            val finishDateTime = DateFormatUtils().getDate(finishDate, "yyyy-MM-dd HH:mm:ss")
            if (finishDateTime.before(currentDateTime)) {
                return false
            }
        }

        return true
    }
}
