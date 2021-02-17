package com.croacker.buyersclub.barcodes.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class WebApiPasswordEncoder : PasswordEncoder {

    val encoder = BCryptPasswordEncoder()

    override fun encode(rawPassword: CharSequence?) = encoder.encode(rawPassword)

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?) =
        encoder.matches(rawPassword, encodedPassword)

}