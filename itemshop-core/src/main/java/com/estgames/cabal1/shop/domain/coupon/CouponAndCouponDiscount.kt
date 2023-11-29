package com.estgames.cabal1.shop.domain.coupon

import com.estgames.cabal1.shop.domain.coupon.reward.CouponDiscount

data class CouponAndCouponDiscount(
    var coupon: Coupon?,
    var couponDiscount: CouponDiscount?,
    var applied: Boolean?,
    var appliedOtherItem: Boolean?
)
