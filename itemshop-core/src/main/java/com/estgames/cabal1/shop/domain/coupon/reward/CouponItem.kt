package com.estgames.cabal1.shop.domain.coupon.reward

import jakarta.persistence.*

@Entity
@Table(name = "CouponItem")
class CouponItem(
    @Column
    var content: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponItemId: Long? = null
}