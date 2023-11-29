package com.estgames.cabal1.shop.domain.cart

import com.estgames.cabal1.shop.domain.coupon.Coupon
import com.estgames.cabal1.shop.domain.proudct.Product
import com.estgames.cabal1.shop.exception.cart.CartException
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "Cart")
class Cart(
    @Column
    var userNum: Int? = null,

    @Column
    var isUsed: Boolean? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDateTime: Date? = null,

    @Column
    var quantity: Int = 0,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val cardId: Long? = null

    @OneToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    var product: Product? = null

    @OneToOne(targetEntity = Coupon::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "couponId")
    var coupon: Coupon? = null

    fun getDiscountCash(): BigDecimal {
        val discountCash = product?.getDiscountCash() ?: throw CartException("no product")
        return discountCash * BigDecimal(quantity)
    }

    fun setQuantityAndUpdateCouponId(quantity: Int) {
        this.quantity = quantity
        if (quantity > 1) coupon = null
    }

    fun getCouponId(): Long? {
        return if (coupon == null) 0 else coupon!!.couponId
    }

}