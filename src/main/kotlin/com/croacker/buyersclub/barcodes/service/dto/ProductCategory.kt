package com.croacker.buyersclub.barcodes.service.dto

import com.croacker.buyersclub.barcodes.domain.ProductCategory

data class ProductCategoryDto(val id: Long, val name: String)

data class AddProductCategoryDto(val name: String)

fun ProductCategory.toDto() = this.id?.let { ProductCategoryDto(it, this.name) }

fun AddProductCategoryDto.toEntity() = ProductCategory(name = this.name)

fun ProductCategoryDto.toEntity() = ProductCategory(id = this.id, name = this.name)