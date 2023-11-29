package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageGloPriType
import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType

data class MessageInfo(
    val messages: List<Message>? = null
) {
    fun getNotReadMessageCount(): Int {
        var notReadCount = 0
        messages?.forEach { message ->
            notReadCount += when (message.messageType) {
                MessageGloPriType.PRIVATE_MESSAGE ->
                    if (message.privateMessage?.messageStatusType == null
                        || message.privateMessage!!.messageStatusType == MessageStatusType.NOT_READ
                    ) 1 else 0

                MessageGloPriType.GLOBAL_MESSAGE ->
                    if (message.globalMessage?.messageStatusType == null
                        || message.globalMessage!!.messageStatusType == MessageStatusType.NOT_READ
                    ) 1 else 0

                MessageGloPriType.GLOBAL_MESSAGE_FOR_USER ->
                    if (message.globalMessageForUser?.messageStatusType == null
                        || message.globalMessageForUser!!.messageStatusType == MessageStatusType.NOT_READ
                    ) 1 else 0

                else -> 0
            }
        }
        return notReadCount
    }
}