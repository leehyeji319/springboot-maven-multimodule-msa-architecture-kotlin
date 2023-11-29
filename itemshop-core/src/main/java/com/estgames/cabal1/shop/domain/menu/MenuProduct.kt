package com.estgames.cabal1.shop.domain.menu

import com.estgames.cabal1.shop.domain.proudct.Product
import jakarta.persistence.*


@Entity
@Table(name = "MenuProduct")
class MenuProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    val menuProductId: Long? = null,

    @OneToOne(targetEntity = Product::class)
    @JoinColumn(name = "productId")
    var product: Product? = null,

    @OneToOne(targetEntity = SubMenu::class)
    @JoinColumn(name = "subMenuIdx")
    var subMenu: SubMenu? = null,

    @Column
    var orderNo: Int? = null,
) {

}