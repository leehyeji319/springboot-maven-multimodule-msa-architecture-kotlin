package com.estgames.cabal1.shop.domain.coupon

import com.estgames.cabal1.shop.domain.coupon.enumerable.CouponStatusType
import com.estgames.cabal1.shop.domain.coupon.enumerable.HowToUseType
import com.estgames.cabal1.shop.exception.coupon.CouponException
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Entity
@Table(name = "Coupon")
data class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponId: Long? = null,

    var couponSerial: String? = null,

    @OneToOne(targetEntity = CouponMaster::class)
    @JoinColumn(name = "masterId")
    val couponMaster: CouponMaster? = null,

    var userNum: Int = 0,

    @Enumerated(EnumType.STRING)
    var status: CouponStatusType? = null,

    @Temporal(TemporalType.TIMESTAMP)
    var startDateTime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    var expireDateTime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var useDateTime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var createDateTime: Date? = null
) {


    init {
        this.status = if (userNum == 0) CouponStatusType.NOGIVE else CouponStatusType.UNUSED
        this.startDateTime = couponMaster?.startType?.date(couponMaster!!.startValue)
        this.expireDateTime = couponMaster?.finishType?.date(couponMaster!!.finishValue)
        this.createDateTime = Date()
    }

    constructor(couponMaster: CouponMaster?, userNum: Int, length: Int) : this(
        couponSerial = createSerial(length),
        couponMaster = couponMaster,
        userNum = userNum
    )

    constructor(couponMaster: CouponMaster?, serial: String?, userNum: Int) : this(
        couponSerial = serial,
        couponMaster = couponMaster,
        userNum = userNum,
        status = CouponStatusType.UNUSED
    )

    constructor(couponSerial: String?, couponMaster: CouponMaster?, userNum: Int) : this(
        couponSerial = couponSerial,
        couponMaster = couponMaster,
        userNum = userNum
    )

    override fun toString(): String {
        return "Coupon(couponId=$couponId, couponSerial=$couponSerial, couponMaster=$couponMaster, userNum=$userNum, status=$status, startDateTime=$startDateTime, expireDateTime=$expireDateTime, useDateTime=$useDateTime, createDateTime=$createDateTime)"
    }

    fun userRegisterCheck(userNum: Int): Boolean {
        if (this.userNum != 0) {
            return false
        } else {
            this.userNum = userNum
            this.status = CouponStatusType.UNUSED
            return true
        }
    }

    /*
    에러: 코틀린에서 생성자 안에서 초기화되지 않은 this에 접근하는 것은 금지되어 있습니다.
    여기서 createSerial 함수는 인스턴스 메서드로 선언되어 있어서
    이 메서드는 인스턴스가 완전히 초기화된 후에만 호출할 수 있습니다.
    그러나 주 생성자의 호출이 완료되기 전에 createSerial을 호출하려고 하기 때문에 에러가 발생합니다.

    해결: createSerial을 companion object 안에 정적 메서드로 선언합니다.
    */
    companion object {
        fun createSerial(serialLength: Int): String {
            val availableChar = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "N", "M", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
            val buffer = StringBuffer()
            val random = Random()
            for (serialCount in 0 until serialLength) {
                buffer.append(availableChar[random.nextInt(availableChar.size)])
            }
            return buffer.toString()
        }
    }

    fun used(usedCouponList: List<Coupon>?) {
        confirm()
        when (this.couponMaster?.howToUseType) {
            HowToUseType.ONCE_PER_ACCOUNT -> if (usedCouponList != null && usedCouponList.isNotEmpty()) {
                throw CouponException(CouponException.CouponCautionType.CAUSION_USE_ONLY_ONE_COUPON)
            }
            HowToUseType.ONCE_PER_ACCOUNT_DAILY -> if (usedCouponList != null) {
                val format = SimpleDateFormat("yyyy-MM-dd")
                usedCouponList.firstOrNull { format.format(Date()) == format.format(it.useDateTime) }
                    ?.let { throw CouponException(CouponException.CouponCautionType.CAUTION_PURCHASE_DAILY_COUPON) }
            }
            else -> {}
        }
        this.status = CouponStatusType.USED
        this.useDateTime = Date()
    }

    fun confirm() {
        if (this.couponMaster?.isUse != true) {
            throw CouponException(CouponException.CouponCautionType.CAUTION_COUPON_USE_EXCEPTION)
        }

        val today = Date()

        if (this.startDateTime != null && today.before(this.startDateTime)) {
            throw CouponException(CouponException.CouponCautionType.CAUTION_COUPON_USE_START_EXCEPTION)
        }

        if (this.expireDateTime != null && today.after(this.expireDateTime)) {
            throw CouponException(CouponException.CouponCautionType.CAUTION_COUPON_USE_EXPIRE_EXCEPTION)
        }

        if (this.status == CouponStatusType.USED) {
            throw CouponException(CouponException.CouponCautionType.CAUTION_CART_COUPON_ALREADY)
        }
    }

    fun canUse(): Boolean {
        return try {
            confirm()
            true
        } catch (e: CouponException) {
            false
        }
    }

    fun useStartCheck(): Boolean {
        val today = Date()
        return this.startDateTime?.let { !today.before(it) } ?: true
    }

    fun useExpireCheck(): Boolean {
        val today = Date()
        return this.expireDateTime?.let { !today.after(it) } ?: true
    }

    fun howToUseCheck(couponList: List<Coupon>?): Boolean {
        when (this.couponMaster?.howToUseType) {
            HowToUseType.ONCE_PER_ACCOUNT -> if (couponList != null && couponList.isNotEmpty()) {
                return false
            }
            HowToUseType.ONCE_PER_ACCOUNT_DAILY -> if (couponList != null) {
                val format = SimpleDateFormat("yyyy-MM-dd")
                return couponList.none { format.format(it.useDateTime) == format.format(Date()) }
            }
            else -> {}
        }
        return true
    }

    fun couponUsedStatusChange() {
        this.status = CouponStatusType.USED
        this.useDateTime = Date()
    }

    fun couponNotUsedStatusChange() {
        this.status = CouponStatusType.UNUSED
        this.useDateTime = null
    }

    fun getCouponDiscount(): BigDecimal? {
        return null
    }
}