package com.croacker.buyersclub.barcodes.service.dto

import com.croacker.buyersclub.barcodes.domain.Product

data class ProductDto(
    val id: Long,
    val barcode: String,
    val name: String,
    val brandId: Long,
    val categoryId: Long,
)

data class AddProductDto(
    val barcode: String,
    val name: String,
    val brandId: Long,
    val categoryId: Long,
)

fun Product.toDto() = this.id?.let { ProductDto(it, this.barcode, this.name, this.brandId, this.categoryId) }

fun AddProductDto.toEntity() = Product(
    barcode = this.barcode,
    name = this.name,
    brandId = this.brandId,
    categoryId = this.categoryId,
)

fun ProductDto.toEntity() = Product(this.id, this.barcode, this.name, this.brandId, this.categoryId)