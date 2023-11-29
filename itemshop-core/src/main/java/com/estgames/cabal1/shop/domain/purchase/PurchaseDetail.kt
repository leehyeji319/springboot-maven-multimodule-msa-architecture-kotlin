package com.estgames.cabal1.shop.domain.purchase

import com.estgames.cabal1.shop.domain.coupon.Coupon
import com.estgames.cabal1.shop.domain.coupon.enumerable.CouponType
import com.estgames.cabal1.shop.domain.proudct.Product
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "PurchaseDetail")
class PurchaseDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var purchaseDetailId: Long? = null,

    @Column
    var quantity: Int? = null,

    @OneToOne
    @JoinColumn(name = "productId")
    var product: Product? = null,

    @OneToOne
    @JoinColumn(name = "couponId")
    var coupon: Coupon? = null,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchaseId")
    var purchase: Purchase? = null,

    @Column
    var price: BigDecimal? = null,

    @Column
    var cash: BigDecimal? = null,

    @Column
    var cashBonus: BigDecimal? = null,

    @Column(name = "tranno")
    var tranNo: Long? = null,


    ) {
    @Transient
    fun getPurchaseDate(): Date? = purchase?.purchaseDate

    @Transient
    fun setPurchaseResult(message: String) {
        message.split("\\|").also {
            tranNo = it[0].toLong()
            cash = BigDecimal.valueOf(it[1].toLong())
            cashBonus = BigDecimal.valueOf(it[2].toLong())
        }
    }

    @Transient
    fun setPurchaseResultThroughPayletter(message: String) {
        message.split("\\|").also {
            tranNo = it[0].toLong()
            cash = BigDecimal(it[1])
            cashBonus = BigDecimal(it[2])
        }
    }

}
