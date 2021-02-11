package com.croacker.buyersclub.barcodes.domain

import org.springframework.data.annotation.Id

data class ProductCategory(
    @Id
    val id: Long? = null,
    val name: String,
)
