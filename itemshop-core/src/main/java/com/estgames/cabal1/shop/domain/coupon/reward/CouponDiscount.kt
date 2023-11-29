package com.estgames.cabal1.shop.domain.coupon.reward

import com.estgames.cabal1.shop.domain.coupon.enumerable.DiscountScope
import com.estgames.cabal1.shop.domain.coupon.enumerable.DiscountType
import com.estgames.cabal1.shop.domain.proudct.enumerable.ProductDiscountType
import jakarta.persistence.*
import java.math.BigDecimal
import java.math.RoundingMode

@Entity
@Table(name = "CouponDiscount")
class CouponDiscount(
    @Column
    var discountName: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var discountType: DiscountType,

    @Column
    @Enumerated(EnumType.STRING)
    var discountScope: DiscountScope,

    @Column
    var amount: String? = null,

    ) {
    @Id
    @GeneratedValue
    val couponDiscountId: Long? = null

    @OneToMany(targetEntity = CouponDiscountEntry::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "couponDiscountId")
    val couponDiscountEntryList: List<CouponDiscountEntry> = arrayListOf()

    fun discountApplyProductPrice(discountCash: BigDecimal, quantity: Int): BigDecimal {
        val bigAmount = BigDecimal(amount)
        return when (discountType) {
            DiscountType.SALES_CASH -> discountCash.multiply(BigDecimal(quantity)).subtract(bigAmount)
            DiscountType.SALES_PERCENTAGE -> {
                if (quantity == 1) {
                    discountCash.divide(BigDecimal(100)).multiply(BigDecimal(100).subtract(bigAmount))
                } else {
                    discountCash.multiply(BigDecimal(quantity - 1)).add(
                        discountCash.divide(BigDecimal(100)).multiply(BigDecimal(100).subtract(bigAmount))
                    )
                }
            }

            DiscountType.FIXED_CASH -> discountCash.multiply(BigDecimal(quantity))
                .subtract(discountCash.subtract(bigAmount))

            else -> discountCash
        }
    }

    /**
    퍼센트 할인에만 라운딩모드적용
     */
    fun discountApplyProductPriceActRound(discountCash: BigDecimal, quantity: Int): BigDecimal {
        val bigAmount = BigDecimal(amount)
        return when (discountType) {
            DiscountType.SALES_CASH -> discountCash.multiply(BigDecimal(quantity)).subtract(bigAmount)
            DiscountType.SALES_PERCENTAGE -> {
                if (quantity == 1) {
                    discountCash.divide(BigDecimal(100)).multiply(BigDecimal(100).subtract(bigAmount))
                        .setScale(0, RoundingMode.HALF_DOWN)
                } else {
                    discountCash.multiply(BigDecimal(quantity - 1)).add(
                        discountCash.divide(BigDecimal(100)).multiply(BigDecimal(100).subtract(bigAmount))
                    ).setScale(0, RoundingMode.HALF_DOWN)
                }
            }

            DiscountType.FIXED_CASH -> {
                val discountProductCashByQuantity = discountCash.multiply(BigDecimal(quantity))
                val discountAmountByQuantity = bigAmount.multiply(BigDecimal(quantity))

                if (discountProductCashByQuantity.subtract(discountAmountByQuantity) > BigDecimal.ZERO) {
                    discountAmountByQuantity
                } else {
                    discountAmountByQuantity.multiply(BigDecimal(-1))
                }
            }

            else -> discountCash
        }
    }

    fun discountPrice(discountCash: BigDecimal): BigDecimal {
        val bigAmount = BigDecimal(amount)
        return when (discountType) {
            DiscountType.SALES_CASH -> if (bigAmount >= discountCash) BigDecimal.ZERO else bigAmount
            DiscountType.SALES_PERCENTAGE -> discountCash.divide(BigDecimal(100)).multiply(bigAmount)
            DiscountType.FIXED_CASH -> discountCash.subtract(bigAmount)
            else -> BigDecimal.ZERO
        }
    }

    /**
    퍼센트 할인에만 라운딩모드적용
     */
    fun discountPriceActRound(discountCash: BigDecimal): BigDecimal {
        val bigAmount = BigDecimal(amount)
        return when (discountType) {
            DiscountType.SALES_CASH -> if (bigAmount >= discountCash) BigDecimal.ZERO else bigAmount
            DiscountType.SALES_PERCENTAGE -> discountCash.divide(BigDecimal(100)).multiply(bigAmount)
                .setScale(0, RoundingMode.HALF_UP)

            DiscountType.FIXED_CASH -> if (bigAmount >= discountCash) BigDecimal.ZERO else discountCash.subtract(
                bigAmount
            )

            else -> BigDecimal.ZERO
        }
    }

    fun getCouponType(discountType: DiscountType?): Int {
        return when (discountType) {
            DiscountType.SALES_PERCENTAGE -> 1
            DiscountType.SALES_CASH, DiscountType.FIXED_CASH -> 2
            else -> 0
        }
    }

}