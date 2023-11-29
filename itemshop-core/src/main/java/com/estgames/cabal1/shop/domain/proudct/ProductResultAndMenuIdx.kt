package com.estgames.cabal1.shop.domain.proudct

import com.mysema.query.SearchResults;

data class ProductResultAndMenuIdx(
    val productSearchResults: SearchResults<Product>? = null,
    val mainIdx: Long = 0L,
    val subIdx: Long = 0L
)