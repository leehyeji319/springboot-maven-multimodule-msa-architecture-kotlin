package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "PrivateMessage")
class PrivateMessage(
    @Column
    var userNum: Int? = null,

    @Column
    var readDate: Date? = null,

    @Column
    var deleteDate: Date? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var messageStatusType: MessageStatusType? = null,

    @ManyToOne(targetEntity = MessageContent::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "messageContentId")
    var messageContent: MessageContent? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val privateMessageId: Long? = null


}
