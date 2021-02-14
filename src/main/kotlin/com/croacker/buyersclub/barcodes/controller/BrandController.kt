package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.BrandService
import com.croacker.buyersclub.barcodes.service.dto.BrandDto
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
import reactor.core.publisher.Flux
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/brand")
@AllArgsConstructor
@Slf4j
@Tag(name = "Brand", description = "Бренд")
@SecurityRequirement(name = "bearerAuth")
class BrandController(val service: BrandService) {

    @Operation(operationId = "listBrands", summary = "Список брендов")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Бренды",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = List::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе",
            content = []
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = []), ApiResponse(
            responseCode = "404",
            description = "Магазины не найдены",
            content = []
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = [])]
    )
    @GetMapping
    fun listBrands(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int,
        @RequestParam(value = "sort", defaultValue = "name")sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: Sort.Direction): Flux<BrandDto?> {
        return service.findAll(PageRequest.of(page, size, direction, sort))
    }

    @Operation(operationId = "getBrand", summary = "Получить бренд по идентификатору")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Чек",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BrandDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"), ApiResponse(
            responseCode = "404",
            description = "Бренд не найдена"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @GetMapping(path = ["/{id}"])
    fun getBrand(@PathVariable id: Long?): Mono<BrandDto?>{
        return Mono.empty()
    }

}