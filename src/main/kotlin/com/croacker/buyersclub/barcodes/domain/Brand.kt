package com.croacker.buyersclub.barcodes.domain

import org.springframework.data.annotation.Id

data class Brand(
    @Id
    val id: Long? = null,
    val name: String,
)
