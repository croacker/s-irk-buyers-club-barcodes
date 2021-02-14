package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.service.dto.BarcodeImageDto
import com.croacker.buyersclub.barcodes.service.dto.ProductDto
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@Slf4j
class BarcodeService(val productService: ProductService) {

    fun processImage(img: BarcodeImageDto): Mono<ProductDto> {
        System.out.println(img.img)
        return Mono.empty();
    }

}