package com.estgames.cabal1.shop.domain.purchase

import java.math.BigDecimal

data class TemporaryCashAmount(
    var canUseBonusCashAmount: BigDecimal = BigDecimal.ZERO,
    var onlyUseCashAmount: BigDecimal = BigDecimal.ZERO
) {
    fun addCanUseBonusCashAmount(t: BigDecimal) {
        this.canUseBonusCashAmount = this.canUseBonusCashAmount.add(t)
    }

    fun addOnlyUseCashAmount(t: BigDecimal) {
        this.onlyUseCashAmount = this.onlyUseCashAmount.add(t)
    }

    fun setOnlyUseCashAmount(amount: BigDecimal) {
        this.onlyUseCashAmount = amount
    }

    fun setOnlyUseCashAmount(amount: Double) {
        this.onlyUseCashAmount = BigDecimal.valueOf(amount)
    }

    fun setOnlyUseCashAmount(amount: Int) {
        this.onlyUseCashAmount = BigDecimal.valueOf(amount.toLong())
    }

    fun setCanUseBonusCashAmount(amount: BigDecimal) {
        this.canUseBonusCashAmount = amount
    }

    fun setCanUseBonusCashAmount(amount: Double) {
        this.canUseBonusCashAmount = BigDecimal.valueOf(amount)
    }

    fun setCanUseBonusCashAmount(amount: Int) {
        this.canUseBonusCashAmount = BigDecimal.valueOf(amount.toLong())
    }

    fun getTotalAmount(): BigDecimal {
        return this.canUseBonusCashAmount.add(this.onlyUseCashAmount)
    }
}