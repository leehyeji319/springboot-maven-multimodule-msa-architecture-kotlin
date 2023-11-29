package com.estgames.cabal1.shop.domain.menu

import com.estgames.cabal1.shop.domain.common.ViewType
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "MainMenu")
class MainMenu(
    @Column
    var menuName: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var viewType: ViewType? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var menuType: MenuType? = null,

    @Column
    var orderNo: Int? = null,

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var regDate: Date? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val menuMainIdx: Long? = null

    @OneToMany(mappedBy = "mainMenu", fetch = FetchType.EAGER)
    @OrderBy("orderNo asc")
    val subMenu: MutableList<SubMenu> = mutableListOf()

    fun getSubMenu(): List<SubMenu> =
        subMenu.filter { it.viewType != ViewType.CLOSED }

    fun canShow(isAdmin: Boolean): Boolean =
        when (viewType) {
            ViewType.OPEN -> true
            ViewType.ADMIN -> isAdmin
            else -> false
        }

}