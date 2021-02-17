package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.UserService
import com.croacker.buyersclub.barcodes.service.auth.AuthRequest
import com.croacker.buyersclub.barcodes.service.auth.AuthResponse
import com.croacker.buyersclub.barcodes.service.dto.ProductDto
import com.croacker.buyersclub.barcodes.service.dto.UserDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
@Tag(name = "User", description = "Пользователи")
@SecurityRequirement(name = "bearerAuth")
class UserController(val service: UserService) {

    @Operation(operationId = "listUsers", summary = "Список пользователей")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Пользователи",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = List::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе",
            content = []
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = []
        ), ApiResponse(
            responseCode = "404",
            description = "Пользователи не найдены",
            content = []
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = [])]
    )
    @GetMapping
    fun listUsers(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int,
        @RequestParam(value = "sort", defaultValue = "name")sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: Sort.Direction): Flux<UserDto?> {
        return service.findAll(PageRequest.of(page, size, direction, sort))
    }

    @Operation(operationId = "getUser", summary = "Получить пользователя по идентификатору")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Пользователь",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = UserDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"), ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @GetMapping(path = ["/{id}"])
    fun getUser(@PathVariable id: Long): Mono<UserDto?>{
        return service.findById(id)
    }

    @Operation(operationId = "login", summary = "Авторизация")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Токен",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = UserDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"), ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @PostMapping(path = ["/auth"])
    fun login(@RequestBody request: AuthRequest): Mono<ResponseEntity<AuthResponse>>{
        return service.auth(request)
            .map { ResponseEntity.ok(it) }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
    }

}