package com.estgames.cabal1.shop.domain.item

import com.estgames.cabal1.shop.domain.item.enumerable.ItemType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Item")
class Item(
    @Column
    var itemName: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var itemType: ItemType? = null,

    @Column
    @Temporal(TemporalType.DATE)
    var updateDate: Date? = null,

    //game item
    @Column
    var itemIdx: Long? = null,

    //byte[] itemOption;
    @Column
    var itemOption: Long? = null,

    @Column
    var durationId: String? = null,

    @Column
    var durationIdx: Int? = null,

    @Column
    // premium item
    var serviceType: Int? = null,

    @Column
    var addDay: Int? = null,

    @Column
    var addSecond: Int? = null,

) {
    @Id
    @GeneratedValue
    var itemId: Long? = null

    fun getItemType() : ItemType? {
        return itemType
    }

    fun toGameItem(item: Item) {
        itemId = item.itemId
        itemName = item.itemName
        itemType = ItemType.GAME
        itemIdx = item.itemIdx
        itemOption = item.itemOption
        durationId = item.durationId
        durationIdx = item.durationIdx
        serviceType = 0
        addDay = 0
        addSecond = 0
    }

    fun toPremiumItem(item: Item) {
        itemId = item.itemId
        itemName = item.itemName
        itemType = ItemType.PREMIUM
        itemIdx = 0
        itemOption = null
        durationId = null
        durationIdx = 0
        serviceType = item.serviceType
        addDay = item.addDay
        addSecond = item.addSecond
    }

    fun toVoucherItem(item: Item) {
        itemId = item.itemId
        itemName = item.itemName
        itemType = ItemType.VOUCHER
        itemIdx = item.itemIdx
        itemOption = item.itemOption
        durationId = item.durationId
        durationIdx = item.durationIdx
        serviceType = 0
        addDay = 0
        addSecond = 0
    }

    fun toLeveledCharacterItem(item: Item) {
        itemId = item.itemId
        itemName = item.itemName
        itemType = ItemType.LVCHAR
        itemIdx = item.itemIdx
        itemOption = item.itemOption
        durationId = item.durationId
        durationIdx = item.durationIdx
        serviceType = 0
        addDay = 0
        addSecond = 0
    }

    fun toClassicTicketItem(item: Item) {
        itemId = item.itemId
        itemName = item.itemName
        itemType = ItemType.CLASSIC_TICKET
        itemIdx = item.itemIdx
        itemOption = item.itemOption
        durationId = item.durationId
        durationIdx = item.durationIdx
        serviceType = 0
        addDay = 0
        addSecond = 0
    }
}
