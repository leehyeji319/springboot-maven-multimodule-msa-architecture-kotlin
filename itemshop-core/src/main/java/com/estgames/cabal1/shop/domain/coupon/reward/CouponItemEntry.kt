package com.estgames.cabal1.shop.domain.coupon.reward

import com.estgames.cabal1.shop.domain.coupon.enumerable.DateType
import com.estgames.cabal1.shop.domain.item.enumerable.ItemType
import jakarta.persistence.*

@Entity
@Table(name = "CouponItemEntry")
class CouponItemEntry(
    @Column
    var couponItemId: Long? = null,

    @Column
    var itemId: Int? = null,

    @Column
    var quantity: Int? = null,

    @Column
    var itemType: Int? = null,

    @Column
    var expiredType: DateType,

    @Column
    var expiredValue: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponItemEntryId: Long? = null
}