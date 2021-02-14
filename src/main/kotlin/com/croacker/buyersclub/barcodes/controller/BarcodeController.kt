package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.BarcodeService
import com.croacker.buyersclub.barcodes.service.ProductService
import com.croacker.buyersclub.barcodes.service.dto.BarcodeImageDto
import com.croacker.buyersclub.barcodes.service.dto.ProductDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/barcode")
@AllArgsConstructor
@Slf4j
@Tag(name = "Barcode", description = "Штрихкод")
@SecurityRequirement(name = "bearerAuth")
class BarcodeController(val service: BarcodeService, val productService: ProductService) {

    @Operation(operationId = "getProductByBarcode", summary = "Получить товар по штрихкоду")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Товар",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ProductDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"), ApiResponse(
            responseCode = "404",
            description = "Товар не найдена"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @GetMapping(path = ["/{barcode}"])
    fun getProductByBarcode(@PathVariable barcode: String): Mono<ProductDto>{
        return productService.findByBarcode(barcode)
    }

    @Operation(operationId = "postBarcodeImage", summary = "Получить товар по изображению штрихкода")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Товар",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ProductDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Ошибка в запросе"
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"
        ), ApiResponse(
            responseCode = "404",
            description = "Товар не найдена"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @PostMapping
    fun postBarcodeImage(@RequestBody dto: BarcodeImageDto): Mono<ProductDto> {
        return service.processImage(dto)
    }

}