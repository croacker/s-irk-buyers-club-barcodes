package com.croacker.buyersclub.barcodes.service.dto

import com.croacker.buyersclub.barcodes.domain.User
import com.croacker.buyersclub.barcodes.domain.UserRole

data class UserDto(
    val id: Long,
    val username: String,
    val userRole: UserRole
)

data class AddUserDto(
    val username: String,
    val password: String,
    val userRole: UserRole
)

fun User.toDto() = this.id?.let { UserDto(this.id, this.username, this.userRole) }

fun AddUserDto.toEntity() = User(username = this.username, password = this.password, userRole = this.userRole)