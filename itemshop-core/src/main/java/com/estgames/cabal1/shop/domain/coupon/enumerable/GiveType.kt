package com.estgames.cabal1.shop.domain.coupon.enumerable

enum class GiveType(val meesage: String) {
    ITEM("아이템"),
    DISCOUNT("할인"),
    FIX_DATE("날짜 고정(yyyy-MM-dd HH:mm:ss"),
    UNLIMITED("제한 없음");
}