package com.estgames.cabal1.shop.domain.proudct.enumerable

enum class IconType {
    NONE, GIFTBOX, PET;

    fun getAddClass(): String {
        return when (this) {
            NONE -> ""
            GIFTBOX -> ""
            PET -> "pet"
        }
    }
}