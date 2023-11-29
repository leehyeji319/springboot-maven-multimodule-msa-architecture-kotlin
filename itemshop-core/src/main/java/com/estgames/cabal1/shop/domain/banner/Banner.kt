package com.estgames.cabal1.shop.domain.banner

import jakarta.persistence.*
import java.util.Date

@Table(name = "Banner")
@Entity
class Banner(
    @Column
    var bannerKey: String?,
    @Column
    var creater: String?,
    @Column
    var userName: String?,
    @Column
    var bannerDescription: String?,
    @Column
    @Enumerated(EnumType.STRING)
    var bannerType: BannerType,
    @Column
    var slideVelocity: Int?,
    @Column
    var isShow: Boolean?,
    @Column
    var regDate: Date?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bannerId: Long? = null

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "bannerId")
    val bannerImgs: List<BannerImg> = emptyList()

}