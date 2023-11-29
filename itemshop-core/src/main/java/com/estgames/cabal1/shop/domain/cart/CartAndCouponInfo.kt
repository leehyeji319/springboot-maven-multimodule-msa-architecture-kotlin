package com.estgames.cabal1.shop.domain.cart

import com.estgames.cabal1.shop.domain.coupon.Coupon
import com.estgames.cabal1.shop.domain.coupon.CouponDiscount
import com.estgames.cabal1.shop.exception.cart.CartException
import java.math.BigDecimal

data class CartAndCouponInfo(
    val cart: Cart,
    val coupon: Coupon?,
    val discount: CouponDiscount?
) {
    fun getCashAppliedCoupon(): BigDecimal {
        return try {
            if (discount == null) cart.getDiscountCash()
            else discount.discountApplyProductPrice(cart.product?.getDiscountCash(), cart.quantity)
        } catch (e: CartException) {
            BigDecimal.ZERO
        }
    }

    fun getCashRoundAppliedCoupon(): BigDecimal {
        return try {
            if (discount == null) {
                cart.getDiscountCash()
            } else {
                discount.discountApplyProductPriceActRound(cart.product?.getDiscountCash(), cart.quantity)
            }
        } catch (e: CartException) {
            BigDecimal.ZERO
        }
    }

    fun getDiscountCash(): BigDecimal {
        val cash = cart.product?.cash?.multiply(BigDecimal.valueOf(cart.quantity.toLong())) ?: BigDecimal.ZERO
        val discountCash = getCashAppliedCoupon()
        return cash.subtract(discountCash)
    }

    fun getDiscountCashRound(): BigDecimal {
        val cash = cart.product?.cash?.multiply(BigDecimal.valueOf(cart.quantity.toLong())) ?: BigDecimal.ZERO
        val discountCash = getCashRoundAppliedCoupon()
        return cash.subtract(discountCash)
    }

    //TODO: 여기 이상함 봐야해
    fun getCouponDiscountCash(): BigDecimal {
        return try {
            val discountCash = getDiscountCash()
            discount?.discountPrice(discountCash) ?: BigDecimal.ZERO
        } catch (e: CartException) {
            // Log the error or handle it as necessary
            BigDecimal.ZERO
        }
    }

    fun getCouponDiscountCashRound(): BigDecimal {
        return try {
            val discountCash = getDiscountCash()
            discount?.discountPriceActRound(discountCash) ?: BigDecimal.ZERO
        } catch (e: CartException) {
            // Log the error or handle it as necessary
            BigDecimal.ZERO
        }
    }
}