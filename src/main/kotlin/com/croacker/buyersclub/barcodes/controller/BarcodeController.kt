package com.croacker.buyersclub.barcodes.controller

import io.swagger.v3.oas.annotations.tags.Tag
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/barcode")
@AllArgsConstructor
@Slf4j
@Tag(name = "Barcode", description = "Штрихкод")
class BarcodeController {
}