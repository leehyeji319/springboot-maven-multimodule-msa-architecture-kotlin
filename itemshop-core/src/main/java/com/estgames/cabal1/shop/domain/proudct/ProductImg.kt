package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.proudct.enumerable.ProductImgType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.Date

@DynamicUpdate
@Entity
@Table(name = "productImg")
class ProductImg(
    @Column
    var imgLink: String? = null,

    @Column
    var orderNum: Int? = null,

    @Column
    var isUse: Boolean? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var productImgType: ProductImgType,

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var regDate: Date? = null,

    @Column
    var productId: Long? = null
) {
    @Id
    @GeneratedValue
    val imgId: Long? = null

}
