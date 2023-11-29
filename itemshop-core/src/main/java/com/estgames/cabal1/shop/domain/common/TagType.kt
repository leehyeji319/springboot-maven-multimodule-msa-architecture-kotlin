package com.estgames.cabal1.shop.domain.common

enum class TagType(val message: String) {
    NONE("NONE"),
    BEST("BEST"),
    NEW("NEW"),
    HOT("HOT"),
    EVENT("EVENT"),
    SPECIAL("SPECIAL"),
    SALE("SALE"),
    FREE("FREE");

    fun getAddClass(): String = when (this) {
        NONE -> "not"
        BEST -> "best"
        NEW -> "new"
        HOT -> "hot_1 hot"
        EVENT -> "event"
        SPECIAL -> "sc"
        SALE -> "sale"
        FREE -> "free"
    }

    fun getSmallAddClass(): String = when (this) {
        BEST -> "c_best"
        HOT -> "c_hot"
        NEW -> "c_new"
        SALE, FREE -> "not"
        else -> "not"
    }

    fun getMenuAddClass(): String = when (this) {
        BEST, NONE -> "not"
        HOT -> "hot_1"
        NEW -> ""
        SALE -> "sale"
        FREE -> "free"
        EVENT -> "event"
        SPECIAL -> "sc"
    }

    companion object {
        fun getAllTagType(): List<TagType> = listOf(
            NONE, NEW, HOT, BEST, SALE, SPECIAL, FREE
        )
    }
}