package com.lnsantos.stock.data.mapper

import com.lnsantos.stock.data.local.CompanyListingEntity
import com.lnsantos.stock.domain.model.CompanyListingDomain

fun CompanyListingEntity.toDomain() = CompanyListingDomain(
    name, symbol, exchange
)

fun CompanyListingDomain.toEntity() = CompanyListingEntity(
    null, name, symbol, exchange
)