package com.estgames.cabal1.shop.domain.purchase

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "Purchase")
class Purchase(
    @Column
    var userNum: Int? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var purchaseDate: Date? = null,

    @Column
    var duration: String? = null,

    @Column
    var price: BigDecimal? = null,

    @Column(name = "tranno")
    var tranNo: Long? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var purchaseId: Long? = null

    @OneToMany(targetEntity = PurchaseDetail::class, fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "purchseId")
    var detailList: List<PurchaseDetail> = listOf()

    fun detailListIdSetting(): List<PurchaseDetail> {
        detailList.forEach { it.purchase = this }
        return detailList
    }

}