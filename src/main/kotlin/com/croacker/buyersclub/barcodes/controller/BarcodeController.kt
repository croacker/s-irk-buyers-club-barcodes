package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.BarcodeService
import com.croacker.buyersclub.barcodes.service.dto.BarcodeImageDto
import com.croacker.buyersclub.barcodes.service.dto.BrandDto
import com.croacker.buyersclub.barcodes.service.dto.ProductDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/barcode")
@AllArgsConstructor
@Slf4j
@Tag(name = "Barcode", description = "Штрихкод")
class BarcodeController(val service: BarcodeService) {

    @Operation(operationId = "getBrand", summary = "Получить товар по изображению штрихкода")
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
        ), ApiResponse(responseCode = "401", description = "Ошибка авторизации"
        ), ApiResponse(
            responseCode = "404",
            description = "Товар не найдена"
        ), ApiResponse(responseCode = "500", description = "Внутренняя ошибка")]
    )
    @PostMapping
    fun getProduct(@RequestBody dto: BarcodeImageDto): Mono<ProductDto> {
        return service.processImage(dto)
    }

}