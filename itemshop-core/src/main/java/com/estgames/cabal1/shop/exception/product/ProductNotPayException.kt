package com.estgames.cabal1.shop.exception.product

class ProductNotPayException : RuntimeException {
    var productName: String? = null
        private set

    constructor() : super()

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(message: String?) : super(message)

    constructor(message: String?, productName: String?) : super(message) {
        this.productName = productName
    }

    constructor(cause: Throwable?) : super(cause)
}