package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.common.TagType
import com.estgames.cabal1.shop.domain.common.ViewType
import com.estgames.cabal1.shop.domain.proudct.enumerable.IconType
import com.estgames.cabal1.shop.domain.proudct.enumerable.ProductDiscountType
import com.estgames.cabal1.shop.exception.product.ProductNotPayException
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.Transient
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "Product")
class Product(
    @Column
    var productName: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var tagType: TagType = TagType.NONE,

    @Column
    @Enumerated(EnumType.STRING)
    var viewType: ViewType = ViewType.OPEN,

    @Column
    @Enumerated(EnumType.STRING)
    var iconType: IconType = IconType.NONE,

    @Column
    var cash: BigDecimal? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var startDate: Date? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var finishDate: Date? = null,

    @Column
    var realAmount: Boolean = false,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var regDate: Date? = null,

    @Column
    var point: Int = 0,

    @Transient
    var bookmark: Boolean = false

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null

    @OneToMany(targetEntity = ProductImg::class, fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "productId")
    var productImgs: List<ProductImg>? = null

    @JsonIgnore
    @OneToMany(targetEntity = ProductDescription::class, fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "productId")
    val productDescriptions: List<ProductDescription>? = null

    @JsonIgnore
    @OneToMany(targetEntity = LimitPolicy::class, fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "productId")
    var limitPolicies: List<LimitPolicy>? = null

    @JsonIgnore
    @OneToOne(targetEntity = ProductDiscount::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    var productDiscount: ProductDiscount? = null;

    @JsonIgnore
    @OneToOne(targetEntity = ProductMessage::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    var productMessage: ProductMessage? = null

    @JsonIgnore
    @OneToMany(targetEntity = ProductItem::class, fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "productId")
    var productItems: Set<ProductItem>? = null


    /**
     * @category 판매 가능 상품 확인
     *
     */
    fun possibilitySell() : Boolean {
        if (viewType == ViewType.CLOSED) {
            throw ProductNotPayException("판매 가능 상품이 아닙니다.", productName)
        }

        val today = Date()

        if (startDate != null && today.before(startDate)) {
            throw ProductNotPayException("판매 시작시간이 안됐습니다.", productName)
        }

        if (finishDate != null && today.after(finishDate)) {
            throw ProductNotPayException("판매 종료된 상품입니다..", productName)
        }

        return true
    }


    fun getDiscountCash(): BigDecimal? {
        return if (isDiscount) {
            when (productDiscount?.discountType) {
                ProductDiscountType.PERCENTAGE -> cash?.divide(BigDecimal(100))
                    ?.multiply(BigDecimal(100) - productDiscount!!.amount!!)
                ProductDiscountType.SUBTRACT -> cash?.subtract(productDiscount!!.amount)
                ProductDiscountType.FIXED -> productDiscount!!.amount
                else -> cash
            }
        } else {
            cash
        }
    }
    
    //TODO: 여기 오류잡기

    @get:Transient
    val isDiscount: Boolean
        get() = productDiscount?.isDiscount ?: false

    fun addProductItem(productItem: ProductItem) {
        productItems = productItems ?: linkedSetOf()
        productItems?.add(productItem)
    }

    fun modProductItem(productItem: ProductItem) {
        productItems?.let {
            it.removeIf { item -> item.productItemIdx == productItem.productItemIdx}
            it.add(productItem)
        }
    }
}