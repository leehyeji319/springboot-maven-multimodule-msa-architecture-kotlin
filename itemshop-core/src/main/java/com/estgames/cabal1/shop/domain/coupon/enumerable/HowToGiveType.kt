package com.estgames.cabal1.shop.domain.coupon.enumerable

enum class HowToGiveType(val message: String) {
    EVENT("이벤트로 지급"),
    PURCHASE("상품 구매 시 지급"),
    NEW_REGISTER("신규 가입자 지급"),
    ACTIVE("활성 유저 최초 한 번 지급"),
    COMEBACK("컴백 유저 지급"),
    INPUT_SERIAL("시리얼 등록"),
    INPUT_SERIAL_MULTI("시리얼 다중 등록"),
    REWARD("전체 보상"),
    INPUT_KEYWORD("키워드 등록");
}