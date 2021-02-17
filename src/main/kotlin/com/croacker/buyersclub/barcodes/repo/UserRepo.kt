package com.croacker.buyersclub.barcodes.repo

import com.croacker.buyersclub.barcodes.domain.User
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepo : ReactiveCrudRepository<User, Long> {
    fun findByIdNotNull(pageable: Pageable): Flux<User>

    fun findByUsername(username: String): Mono<User>

    fun findByUsernameAndPassword(username: String, password: String): Mono<User>
}