package com.estgames.cabal1.shop.domain.menu

enum class MenuType {
    P, B;

    companion object {
        fun typeCheck(type: String): MenuType? = when (type.lowercase()) {
            "p" -> P
            "b" -> B
            else -> null
        }
    }
}