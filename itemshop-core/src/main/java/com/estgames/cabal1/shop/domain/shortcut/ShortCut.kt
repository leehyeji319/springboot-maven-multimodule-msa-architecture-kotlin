package com.estgames.cabal1.shop.domain.shortcut

import jakarta.persistence.*

@Entity
@Table(name = "ShortCut")
class ShortCut(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    val shortCutId: Long? = null,

    @Column
    var name: String? = null,

    @Column
    var url: String? = null,

    @Column
    var liClass: String? = null, //tm(T-point) is(Item Shop)

    @Column
    var orderNo: Int? = null,

    @Column
    var newAlam: Boolean? = null,
) {

}