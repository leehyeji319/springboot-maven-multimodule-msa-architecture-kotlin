package com.estgames.cabal1.shop.domain.common


import org.springframework.data.domain.Page

class Pages(val data: Page<*>) {

    val curCount: Number
        get() {
            val curCount = data.size * (data.number + 1)
            return if (curCount > data.totalElements) {
                data.totalElements
            } else {
                curCount
            }
        }
}