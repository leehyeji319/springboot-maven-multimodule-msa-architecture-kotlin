package com.estgames.cabal1.shop.domain.proudct

import jakarta.persistence.*

@Entity
@Table(name = "ProductMessage")
class ProductMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productId: Long? = null,

    @Column
    var productMessage: String? = null,

    @Column
    var isUse: Int = 0
) {
}
