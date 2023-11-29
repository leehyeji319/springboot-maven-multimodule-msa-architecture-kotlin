package com.estgames.cabal1.shop.domain.move

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "CharacterMoveSubPasswordCheck")
class CharacterMoveSubPasswordCheck(
    @Id
    @Column
    val userNum: Int? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var checkDate: Date? = null,

) {
}