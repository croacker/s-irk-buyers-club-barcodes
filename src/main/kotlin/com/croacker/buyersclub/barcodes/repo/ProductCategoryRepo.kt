package com.croacker.buyersclub.barcodes.repo

import com.croacker.buyersclub.barcodes.domain.ProductCategory
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ProductCategoryRepo : ReactiveCrudRepository<ProductCategory, Long> {
    fun findAll(pageable: Pageable): Flux<ProductCategory>
}