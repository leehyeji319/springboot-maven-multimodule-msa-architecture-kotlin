package com.estgames.cabal1.shop.domain.proudct

import jakarta.persistence.Column
import org.hibernate.resource.jdbc.internal.LogicalConnectionManagedImpl

data class LimitPolicyForAdmin(
    val policy: LimitPolicy,
    val remainCount: Long,
)