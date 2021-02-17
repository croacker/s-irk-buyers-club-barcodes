package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.ProductCategoryService
import com.croacker.buyersclub.barcodes.service.dto.BrandDto
import com.croacker.buyersclub.barcodes.service.dto.ProductCategoryDto
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
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/product-category")
@AllArgsConstructor
@Slf4j
@Tag(name = "ProductCategory", description = "Категории товаров")
@SecurityRequirement(name = "bearerAuth")
class ProductCategoryController(val service: ProductCategoryService) {

    @Operation(operationId = "listProductCategories", summary = "Список категорий товаров")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Категории товаров",
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
    fun listProductCategories(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int,
        @RequestParam(value = "sort", defaultValue = "name")sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: Sort.Direction): Flux<ProductCategoryDto?> {
        return service.findAll(PageRequest.of(page, size, direction, sort))
    }

    @Operation(operationId = "getProductCategory", summary = "Получить категорию товаров по идентификатору")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Категория товаров",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ProductCategoryDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"), ApiResponse(
            responseCode = "404",
            description = "Категория товаров не найдена"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @GetMapping(path = ["/{id}"])
    fun getProductCategory(@PathVariable id: Long): Mono<ProductCategoryDto?>{
        return service.findById(id)
    }

}