package com.estgames.cabal1.shop.domain.coupon

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "CouponKey")
class CouponKey {
    @Id
    val couponMasterId: Long? = null

    @Column
    var couponKey: String? = null
}