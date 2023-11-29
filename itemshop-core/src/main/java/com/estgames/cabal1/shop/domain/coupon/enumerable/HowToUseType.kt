package com.estgames.cabal1.shop.domain.coupon.enumerable

enum class HowToUseType(val message: String) {
    UNLIMITED("제한 없음"),
    ONCE_PER_ACCOUNT("계정당 한 번"),
    ONCE_PER_ACCOUNT_DAILY("계정당 일별로 한 번");
}