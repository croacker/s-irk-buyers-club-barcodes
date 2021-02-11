package com.croacker.buyersclub.barcodes.domain

import org.springframework.data.annotation.Id

data class Product(
    @Id
    val id: Long? = null,
    val barcode: String,
    val name: String,
    val categoryId: Long,
    val brandId: Long,
)
