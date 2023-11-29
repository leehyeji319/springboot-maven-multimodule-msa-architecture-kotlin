package com.estgames.cabal1.shop.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "AdminUser")
class AdminUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    var adminUserId: Long? = null,

    @Column
    var userId: String? = null

) {
}