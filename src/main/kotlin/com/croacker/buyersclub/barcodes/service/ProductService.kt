package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.repo.ProductRepo
import com.croacker.buyersclub.barcodes.service.dto.AddProductDto
import com.croacker.buyersclub.barcodes.service.dto.toDto
import com.croacker.buyersclub.barcodes.service.dto.toEntity
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(private val repo: ProductRepo) {

    fun findAll(pageable: Pageable) = repo.findByIdNotNull(pageable).map { e -> e.toDto() }

    fun findById(id: Long) = repo.findById(id).map { e -> e.toDto() }

}