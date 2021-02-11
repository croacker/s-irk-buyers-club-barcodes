package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.repo.ProductCategoryRepo
import com.croacker.buyersclub.barcodes.service.dto.AddProductCategoryDto
import com.croacker.buyersclub.barcodes.service.dto.AddProductDto
import com.croacker.buyersclub.barcodes.service.dto.toEntity
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductCategoryService(private val repo: ProductCategoryRepo) {

    suspend fun findAll(pageable: Pageable) = repo.findAll(pageable).asFlow()

    suspend fun findById(id: Long) = repo.findById(id).awaitFirstOrNull()

    suspend fun save(dto: AddProductCategoryDto) = repo.save(dto.toEntity()).awaitFirstOrNull()

}