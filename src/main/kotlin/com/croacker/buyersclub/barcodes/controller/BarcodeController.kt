package com.croacker.buyersclub.barcodes.controller

import com.croacker.buyersclub.barcodes.service.BarcodeService
import com.croacker.buyersclub.barcodes.service.dto.BarcodeImageDto
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/barcode")
@AllArgsConstructor
@Slf4j
@Tag(name = "Barcode", description = "Штрихкод")
class BarcodeController(val service: BarcodeService) {

    @PostMapping
    fun getBrand(@RequestBody dto: BarcodeImageDto){
        service.processImage(dto)
    }

}