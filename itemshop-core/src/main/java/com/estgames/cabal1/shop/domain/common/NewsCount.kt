package com.estgames.cabal1.shop.domain.common

import com.estgames.cabal1.shop.domain.news.News
import com.estgames.cabal1.shop.domain.news.NewsType

data class NewsCount(
    var order: Long = 0,
    var coupon: Long = 0,
    var gift: Long = 0,
    var cart: Long = 0,
    var refill: Long = 0,
    var bookmark: Long = 0
) {
    constructor(list: List<News>?) : this() {
        list?.forEach { item ->
            when (item.newsType) {
                NewsType.ORDER -> order++
                NewsType.COUPON -> coupon++
                NewsType.GIFT -> gift++
                NewsType.CART -> cart++
                NewsType.REFILL -> refill++
                NewsType.BOOKMARK -> bookmark++
                else -> {}
            }
        }
    }

    val totalCount: Long
        get() = order + coupon + gift + cart + refill + bookmark
}

