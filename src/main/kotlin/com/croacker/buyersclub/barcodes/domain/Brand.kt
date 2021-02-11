package com.croacker.buyersclub.barcodes.domain

import com.croacker.buyersclub.barcodes.service.dto.BrandDto
import org.springframework.data.annotation.Id

data class Brand(
    @Id
    val id: Long? = null,
    val name: String,
)
