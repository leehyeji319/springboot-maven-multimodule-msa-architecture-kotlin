package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "GlobalMessageForUser")
class GlobalMessageForUser(
    @Column
    var userNum: Int? = null,

    @Column
    var readDate: Date? = null,

    @Column
    var deleteDate: Date? = null,

    @ManyToOne(targetEntity = GlobalMessage::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "globalMessageId")
    var globalMessage: GlobalMessage? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var messageStatusType: MessageStatusType? = null,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val globalMessageForUserId: Long? = null
}