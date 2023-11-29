package com.estgames.cabal1.shop.domain.news

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "News")
class News(
    @Column
    @Enumerated(EnumType.STRING)
    var newsType: NewsType? = null,

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var regDate: Date? = null,

    @Column
    var state: Int? = null,

    @Column
    var userNum: Int? = null,


) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    var newsId: Long? = null

}