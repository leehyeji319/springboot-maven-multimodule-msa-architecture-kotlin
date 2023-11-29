package com.estgames.cabal1.shop.domain.menu

import com.estgames.cabal1.shop.domain.common.TagType
import com.estgames.cabal1.shop.domain.common.ViewType
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "SubMenu")
class SubMenu(
    @Column
    var menuName: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var viewType: ViewType? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var tagType: TagType? = null,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menuMainIdx")
    var mainMenu: MainMenu? = null,

    @Column
    var orderNo: Int? = null

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subMenuIdx: Long? = null

    fun canShow(isAdmin: Boolean): Boolean =
        when (viewType) {
            ViewType.OPEN -> true
            ViewType.ADMIN -> isAdmin
            else -> false
        }

}
