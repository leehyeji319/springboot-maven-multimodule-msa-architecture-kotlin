package com.estgames.cabal1.shop.domain.move

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "SubPasswordFailed")
class SubPasswordFailed(
    @Id
    @Column
    val userNum: Int? = null,

    @Column
    var failedCount: Int? = null,

    @Column
    var block: Boolean? = null
) {

    fun addFailedCoun(count: Int) {
        failedCount = failedCount?.plus(count)
    }
}