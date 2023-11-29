package com.estgames.cabal1.shop.domain.bookmark

import com.estgames.cabal1.shop.domain.proudct.Product
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

@Entity
@Table(name = "Bookmark")
class Bookmark(
    @Column
    var userNum: Int? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    var regDate: Date? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookmarkId: Long? = null

    @OneToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    var product: Product? = null
}