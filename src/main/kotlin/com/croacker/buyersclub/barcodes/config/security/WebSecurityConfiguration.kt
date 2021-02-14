package com.croacker.buyersclub.barcodes.config.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.csrf().disable()
            .cors()
            .configurationSource { request ->
                val corsConfig = CorsConfiguration().applyPermitDefaultValues()
                corsConfig.addAllowedMethod(HttpMethod.PUT)
                corsConfig.addAllowedMethod(HttpMethod.OPTIONS)
                corsConfig.addAllowedMethod(HttpMethod.DELETE)
                corsConfig
            }
            .and()
            .authorizeExchange()
            .pathMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/swagger-ui/**")
            .permitAll()
            .pathMatchers("/**")
            .permitAll()
            .and()
            .build()
}