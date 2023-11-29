package com.estgames.cabal1.shop.domain.proudct

import com.estgames.cabal1.shop.domain.item.Item
import com.estgames.cabal1.shop.domain.item.enumerable.ItemType
import jakarta.persistence.*


@Entity
@Table(name = "productItem")
class ProductItem(
    @Column
    var quantity: Int? = null,

    @Column
    var productId: Long? = null,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productItemIdx: Long? = null

    @OneToOne(targetEntity = Item::class)
    @JoinColumn(name = "itemId")
    var item: Item? = null

    fun getItemType(): ItemType? = item?.getItemType()

    fun setItemType(itemType: ItemType) {
        item?.let { it.itemType = itemType }
    }

    @get:Transient
    val itemId: Long?
        get() = item?.itemId
}
