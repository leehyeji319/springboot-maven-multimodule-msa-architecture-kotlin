package com.estgames.cabal1.shop.domain.message

import com.estgames.cabal1.shop.domain.message.enumerable.MessageStatusType
import com.estgames.cabal1.shop.domain.message.enumerable.MessageType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "MessageContent")
class MessageContent(
    @Column
    var title: String? = null,

    @Column
    var regDate: Date? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var messageType: MessageType? = null,

    @Column
    var url: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var messageStatusType: MessageStatusType? = null,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var messageContentId: Long? = null

}
