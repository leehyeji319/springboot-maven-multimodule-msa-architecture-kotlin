package com.estgames.cabal1.shop.domain.common

class ResultReturn<T>(
    var returnCode: Int? = null,
    var message: String? = null,
    var data: T? = null
)