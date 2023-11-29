package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageGloPriType
import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Message")
class Message(
    @Column
    @Enumerated(EnumType.STRING)
    var messageType: MessageGloPriType? = null,

    @OneToOne(targetEntity = GlobalMessage::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "globalMessageId")
    var globalMessage: GlobalMessage? = null,

    @OneToOne(targetEntity = PrivateMessage::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "privateMessageId")
    var privateMessage: PrivateMessage? = null,

    @OneToOne(targetEntity = GlobalMessageForUser::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "globalMessageForUserId")
    var globalMessageForUser: GlobalMessageForUser? = null,

    @Column
    var regDate: Date? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val messageId: Long? = null

    fun isDelete(): Boolean {
        val messageType = when (this.messageType) {
            MessageGloPriType.GLOBAL_MESSAGE -> this.globalMessage?.messageStatusType
            MessageGloPriType.PRIVATE_MESSAGE -> this.privateMessage?.messageStatusType
            else -> this.globalMessageForUser?.messageStatusType
        }
        return messageType == MessageStatusType.DELETE
    }

    fun getContent(): Any? {
        return when (this.messageType) {
            MessageGloPriType.GLOBAL_MESSAGE -> this.globalMessage
            MessageGloPriType.GLOBAL_MESSAGE_FOR_USER -> this.globalMessageForUser?.globalMessage
            MessageGloPriType.PRIVATE_MESSAGE -> this.privateMessage
            else -> null
        }
    }

}