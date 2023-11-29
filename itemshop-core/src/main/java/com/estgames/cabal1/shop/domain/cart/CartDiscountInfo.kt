package com.estgames.cabal1.shop.domain.cart

import java.math.BigDecimal

data class CartDiscountInfo(
    var userNum: Int? = null,
    var cartCartId: Long? = null, // 카트 아이디

    var cartCouponId: Long? = null, // 카트에 적용된 쿠폰 아이디

    var createDateTime: String? = null,
    var isUsed: Boolean? = null, // 카트 상태

    var quantity: Int? = null, // 카트에 담긴 상품 개수

    var cartProductId: Long? = null,
    var couponCouponId: Long? = null, // 쿠폰 테이블의 쿠폰 아이디

    var couponMasterId: Long? = null,
    var cash: BigDecimal? = null, // 상품 원가

    var productDiscountAmount: BigDecimal? = null, // 1개 상품에 대한 상품 할인 가

    var productDiscountType: String? = null,
    var discountApplyProductPrice: BigDecimal? = null, // 여러 상품에 대한 총 상품할인이 적용된 가격

    var discountApplyOneProductPrice: BigDecimal? = null, //상품 한 개에 상품할인이 적용된 가격

    var couponType: String? = null,
    var createdDate: String? = null,
    var description: String? = null,
    var finishType: String? = null,
    var finishValue: String? = null,
    var giveEndDate: String? = null,
    var giveNum: Int? = null,
    var giveStartDate: String? = null,
    var howToGiveType: String? = null,
    var howToUseType: String? = null,
    var imgUrl: String? = null,
    var isGive: Boolean? = null, // 쿠폰 상태

    var isUse: Boolean? = null, // 쿠폰 상태

    var masterName: String? = null,
    var startType: String? = null,
    var startValue: String? = null,
    var couponDiscountId: Long? = null,
    var coupondiscountAmount: String? = null, // 쿠폰 할인 가

    var discountName: String? = null,
    var discountScope: String? = null,
    var discountType: String? = null,
    var discountApplyCouponPrice: BigDecimal? = null, // (상품 할인이 적용 된 가격 혹은 원가에) 쿠폰 할인이 적용 된 가격

    var totalPayment: BigDecimal? = null, // 상품 할인 과 쿠폰 할인이 적용된 가격으로 최종 합계 금액


)