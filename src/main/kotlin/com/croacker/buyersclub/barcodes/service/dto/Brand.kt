package com.croacker.buyersclub.barcodes.service.dto

import com.croacker.buyersclub.barcodes.domain.Brand

data class BrandDto(val id: Long, val name: String)

data class AddBrandDto(val name: String)

fun Brand.toDto() = this.id?.let { BrandDto(it, this.name) }

fun AddBrandDto.toEntity() = Brand(name = this.name)

fun BrandDto.toEntity() = Brand(id = this.id, name = this.name)