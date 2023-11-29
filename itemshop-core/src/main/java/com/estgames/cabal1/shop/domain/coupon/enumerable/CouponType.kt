package com.estgames.cabal1.shop.domain.coupon.enumerable

enum class CouponType(val message: String) {
    GIVE_ITEM("아이템 지급"), //아이템을 지급하는 쿠폰
    GIVE_CASH("캐시 지급"), //캐시를 지급하는 쿠폰
    DISCOUNT("상품 할인"); //상품 할인 쿠폰
}