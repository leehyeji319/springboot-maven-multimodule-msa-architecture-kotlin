package com.estgames.cabal1.shop.domain.banner

import jakarta.persistence.*

@Entity
@Table(name = "BannerImg")
class BannerImg(
    @Column
    var imgLink: String?,
    @Column
    var url: String?,
    @Column
    var description: String?,
    @Column
    var additionDes: String?,
    @Column
    @Enumerated(EnumType.STRING)
    var shoType: ShowType,
    @Column
    var isShow: Boolean?,
    @Column
    var orderNo: Int?,
    @Column
    var bannerId: Long?,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bannerImgId: Long = 0
}