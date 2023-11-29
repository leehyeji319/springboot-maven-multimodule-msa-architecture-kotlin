package com.estgames.cabal1.shop.domain.user.enumerable

enum class AccessType(val access: String, val message: String) {
    GAME("GAME", "게임에서 아이템샵 접근"),
    WEB("WEB", "웹에서 아이템샵 접근")
}