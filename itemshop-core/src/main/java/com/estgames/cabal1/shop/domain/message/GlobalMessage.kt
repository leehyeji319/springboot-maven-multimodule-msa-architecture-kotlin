package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "GlobalMessage")
class GlobalMessage(
    @OneToOne(targetEntity = MessageContent::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "messageContentId")
    var messageContent: MessageContent? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var messageStatusType: MessageStatusType? = null,

    @Column
    var deleteDate: Date? = null,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val globalMessageId: Long? = null

}