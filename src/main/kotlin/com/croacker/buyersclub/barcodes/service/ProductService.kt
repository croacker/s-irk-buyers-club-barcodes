package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.repo.ProductRepo
import com.croacker.buyersclub.barcodes.service.dto.AddProductDto
import com.croacker.buyersclub.barcodes.service.dto.toEntity
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(private val repo: ProductRepo) {

    suspend fun findAll(pageable: Pageable) = repo.findAll(pageable).asFlow()

    suspend fun findById(id: Long) = repo.findById(id).awaitFirstOrNull()

    suspend fun save(dto: AddProductDto) = repo.save(dto.toEntity()).awaitFirstOrNull()

}