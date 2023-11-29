package com.estgames.cabal1.shop.domain.coupon

import com.estgames.cabal1.shop.domain.coupon.enumerable.CouponType
import com.estgames.cabal1.shop.domain.coupon.enumerable.DateType
import com.estgames.cabal1.shop.domain.coupon.enumerable.HowToGiveType
import com.estgames.cabal1.shop.domain.coupon.enumerable.HowToUseType
import com.estgames.cabal1.shop.exception.coupon.CouponMasterException
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "CouponMaster")
class CouponMaster(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var masterId: Long? = null,

    var masterName: String? = null,    // 쿠폰명
    var imgUrl: String? = null,        // 이미지 URL
    var description: String? = null,   // 설명

    @Enumerated(EnumType.STRING)
    var startType: DateType? = null,   // 쿠폰 유효기간 설정
    var startValue: String? = null,

    @Enumerated(EnumType.STRING)
    var finishType: DateType? = null,  // 쿠폰 유효기간 설정
    var finishValue: String? = null,

    @Enumerated(EnumType.STRING)
    var howToGiveType: HowToGiveType? = null,  // 지급방식 이벤트, 시리얼, 전체, 키워드

    @Enumerated(EnumType.STRING)
    var howToUseType: HowToUseType? = null,    // 쿠폰 사용제한 없음, 한번, 일당 한번

    @Enumerated(EnumType.STRING)
    var couponType: CouponType? = null,        // 쿠폰 타입, 아이템 지급, 캐쉬지급, 상품할인

    var giveNum: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var giveStartDate: Date? = null,           // 지급 가능 시작 날짜

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var giveEndDate: Date? = null,             // 지급 가능 마지막 날짜

    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date? = Date(),

    var isUse: Boolean = false,                // 사용가능 여부
    var isGive: Boolean = false                // 지급 가능 여부

) : Serializable {

    constructor(masterName: String?, description: String?) : this() {
        this.masterName = masterName
        this.description = description
    }

    constructor(
        masterName: String?,
        imgUrl: String?,
        description: String?,
        startType: DateType?,
        startValue: String?,
        finishType: DateType?,
        finishValue: String?,
        howToGiveType: HowToGiveType?,
        howToUseType: HowToUseType?,
        couponType: CouponType?,
        giveStartDate: Date?,
        giveEndDate: Date?,
        isUse: Boolean,
        isGive: Boolean,
        giveNum: Long?
    ) : this() {
        this.masterName = masterName
        this.imgUrl = imgUrl
        this.description = description
        this.startType = startType
        this.startValue = startValue
        this.finishType = finishType
        this.finishValue = finishValue
        this.howToGiveType = howToGiveType
        this.howToUseType = howToUseType
        this.couponType = couponType
        this.giveStartDate = giveStartDate
        this.giveEndDate = giveEndDate
        this.createDate = Date()
        this.isUse = isUse
        this.isGive = isGive
        this.giveNum = giveNum
    }

    /**
     * @deprecated
     */
    fun isGiveCoupon() {
        if (!isGive) throw CouponMasterException("지급 가능한 쿠폰이 아닙니다.")
        val today = Date()
        if (giveStartDate != null && today.before(giveStartDate)) {
            throw CouponMasterException("지급 시작시간이 안됐습니다.")
        }
        if (giveEndDate != null && today.after(giveEndDate)) {
            throw CouponMasterException("지급 종료된 상품입니다.")
        }
    }

    fun giveStartCheck(): Boolean {
        val today = Date()
        return if (giveStartDate != null && today.before(giveStartDate)) false else true
    }

    fun giveEndCheck(): Boolean {
        val today = Date()
        return if (giveEndDate != null && today.after(giveEndDate)) false else true
    }

    companion object {
        private const val serialVersionUID = 8426030257245988179L
    }
}