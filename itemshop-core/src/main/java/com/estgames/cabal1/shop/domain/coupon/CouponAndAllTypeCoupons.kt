package com.estgames.cabal1.shop.domain.coupon

import com.estgames.cabal1.shop.domain.coupon.reward.CouponCash
import com.estgames.cabal1.shop.domain.coupon.reward.CouponDiscount
import com.estgames.cabal1.shop.domain.coupon.reward.CouponItem

data class CouponAndAllTypeCoupons(
    var coupon: Coupon?,
    var couponDiscount: CouponDiscount?,
    var couponItem: CouponItem?,
    var couponCash: CouponCash?,
    var menuMainIdx: Long?
)
