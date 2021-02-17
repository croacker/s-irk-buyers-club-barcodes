package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.repo.UserRepo
import com.croacker.buyersclub.barcodes.service.auth.AuthRequest
import com.croacker.buyersclub.barcodes.service.auth.AuthResponse
import com.croacker.buyersclub.barcodes.service.dto.toDto
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val repo: UserRepo) {

    val encoder = BCryptPasswordEncoder()

    fun findAll(pageable: Pageable) = repo.findByIdNotNull(pageable).map { e -> e.toDto() }

    fun findById(id: Long) = repo.findById(id).map { e -> e.toDto() }

    fun findByUsername(username: String) =
        repo.findByUsername(username)

    fun findByUsernameAndPassword(username: String, password: String) =
        repo.findByUsernameAndPassword(username, password)

    fun encode(rawPassword: String) = encoder.encode(rawPassword)

    fun matches(rawPassword: String, encodedPassword: String) = encoder.matches(rawPassword, encodedPassword)

    fun auth(request: AuthRequest): Mono<AuthResponse> {
        TODO("Not yet implemented")
    }

}