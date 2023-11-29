package com.estgames.cabal1.shop.domain.coupon.reward

import jakarta.persistence.*

@Entity
@Table(name = "CouponDiscountEntry")
class CouponDiscountEntry(
    @Column
    var couponDiscountId: Long? = null,

    @Column
    var entryValue: String? = null,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponDiscountEntryId: Long? = null
}
