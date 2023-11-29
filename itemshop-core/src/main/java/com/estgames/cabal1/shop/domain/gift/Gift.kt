package com.estgames.cabal1.shop.domain.gift

import com.estgames.cabal1.shop.domain.proudct.Product
import com.estgames.cabal1.shop.domain.proudct.ProductMessage
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

@Entity
@Table(name = "Gift")
class Gift(
    @Column
    var sendUserNum: Int? = null,

    @Column
    var receiverUserNum: Int? = null,

    @OneToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    var product: Product? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var regDate: Date? = null,

    @Column
    var status: Boolean? = null,

    @Column
    var message: String? = null,

    @Column
    var quantity: Int? = null,

    @Column(name = "tranno")
    var tranNo: Long? = null,



) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val giftId: Long? = null

}