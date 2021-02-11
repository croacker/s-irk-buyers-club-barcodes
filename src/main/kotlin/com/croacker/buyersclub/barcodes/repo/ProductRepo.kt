package com.croacker.buyersclub.barcodes.repo

import com.croacker.buyersclub.barcodes.domain.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ProductRepo : ReactiveCrudRepository<Product, Long> {
    fun findAll(pageable: Pageable): Flux<Product>
}