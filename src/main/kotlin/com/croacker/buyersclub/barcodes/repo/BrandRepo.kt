package com.croacker.buyersclub.barcodes.repo

import com.croacker.buyersclub.barcodes.domain.Brand
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface BrandRepo : ReactiveCrudRepository<Brand, Long> {
    fun findAll(pageable: Pageable): Flux<Brand>
}