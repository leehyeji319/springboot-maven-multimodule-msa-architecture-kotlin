package com.estgames.cabal1.shop.domain.coupon.reward

import jakarta.persistence.*

@Entity
@Table(name = "CouponCash")
class CouponCash(
    @Column
    var amount: Double? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponCashId: Long? = null
}