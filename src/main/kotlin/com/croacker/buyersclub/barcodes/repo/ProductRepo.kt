package com.croacker.buyersclub.barcodes.repo

import com.croacker.buyersclub.barcodes.domain.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductRepo : ReactiveCrudRepository<Product, Long> {
    fun findByIdNotNull(pageable: Pageable): Flux<Product>

    fun findFirst1ByBarcode(barcode: String): Mono<Product>
}