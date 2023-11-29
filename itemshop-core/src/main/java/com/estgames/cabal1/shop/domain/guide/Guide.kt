package com.estgames.cabal1.shop.domain.guide

import com.estgames.cabal1.shop.domain.common.ChannelType
import jakarta.persistence.*
import java.nio.channels.spi.AbstractInterruptibleChannel

@Entity
@Table(name = "Guide")
class Guide(
    @Id
    @Enumerated(EnumType.STRING)
    val channel: ChannelType? = null,

    @Column
    var content: String? = null
) {
}