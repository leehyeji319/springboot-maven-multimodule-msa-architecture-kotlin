package com.estgames.cabal1.shop.domain.banner.product

import com.estgames.cabal1.shop.domain.proudct.Product
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "BannerProduct")
class BannerProduct(
    @Column
    var size: Int?,
    @Column
    var imgUrl: String?,
    @Column
    var url: String?,
    @Column
    var orderNo: Int?,
    @Column
    var isUse: Boolean?,
    @Column
    var regDate: Date?,
    @Column
    @Enumerated(EnumType.STRING)
    var productViewType: ProductViewType,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bannerProduct: Long = 0

    @OneToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    var product: Product? = null

    fun isImage() = productViewType == ProductViewType.LEFT && size != 0
}