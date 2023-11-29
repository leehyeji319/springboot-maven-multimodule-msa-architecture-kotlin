package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.proudct.enumerable.ProductDiscountType
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.Date

@Entity
@Table(name = "ProductDiscount")
class ProductDiscount(
    @Column
    @Enumerated(EnumType.STRING)
    var discountType: ProductDiscountType? = null,

    @Column
    var amount: BigDecimal? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var startDate: Date? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var finishDate: Date? = null,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productDiscountId: Long? = null

    /*
    fun isDiscount(): Boolean {
        val today = Date()
        return when {
            startDate != null && startDate!!.after(today) -> false
            finishDate != null && finishDate!!.before(today) -> false
            else -> true
        }
    }
    */
    @get:Transient
    val isDiscount: Boolean
        get() {
            val today = Date()
            return when {
                startDate != null && startDate!!.after(today) -> false
                finishDate != null && finishDate!!.before(today) -> false
                else -> true
            }
        }
}
