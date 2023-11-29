package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.proudct.enumerable.DesType
import jakarta.persistence.*

@Entity
@Table(name = "ProductDescription")
class ProductDescription(
    @Column
    @Enumerated(EnumType.STRING)
    var desType: DesType? = null,

    @Lob
    @Basic
    @Column
    var content: String? = null,

    @Column
    var productId: Long? = null,


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productDescriptionId: Long? = null
}
