package com.estgames.cabal1.shop.exception.coupon

class CouponException : RuntimeException {
    enum class CouponCautionType {
        CAUSION_USE_ONLY_ONE_COUPON, CAUTION_PURCHASE_DAILY_COUPON, CAUTION_COUPON_USE_EXCEPTION, CAUTION_COUPON_USE_START_EXCEPTION,
        CAUTION_COUPON_USE_EXPIRE_EXCEPTION, CAUTION_CART_COUPON_ALREADY, CAUTION_COUPON_USE_COUNT_EXCESS,
        CAUTION_COUPON_GIVE_START, CAUTION_COUPON_GIVE_EXPIRE
    }

    var exceptionType: CouponCautionType? = null
        private set

    constructor() : super()
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
    constructor(type: CouponCautionType) {
        exceptionType = type
    }

    constructor(cause: Throwable) : super(cause)
}