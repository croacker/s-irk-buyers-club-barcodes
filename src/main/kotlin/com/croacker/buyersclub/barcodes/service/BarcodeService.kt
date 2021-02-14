package com.croacker.buyersclub.barcodes.service

import com.croacker.buyersclub.barcodes.service.dto.BarcodeImageDto
import com.croacker.buyersclub.barcodes.service.dto.ProductDto
import com.google.zxing.BinaryBitmap
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import java.io.ByteArrayInputStream
import java.lang.Exception
import java.util.*
import com.google.zxing.MultiFormatReader
import com.google.zxing.Reader
import com.google.zxing.Result
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.ByteArrayOutputStream
import java.util.function.Function
import java.util.stream.Collectors


@Service
@Slf4j
class BarcodeService(val productService: ProductService) {

    fun processImage(img: BarcodeImageDto): Mono<ProductDto> {
        val bufferedImage = decodeToImage(img.img)
        val reader: Reader = MultiFormatReader()
        val res = reader.decode(extract(bufferedImage))
        return productService.findByBarcode(res.text);
    }

    fun decodeToImage(imageString: String): BufferedImage {
        var image: BufferedImage? = null
        val imageByte: ByteArray
        try {
            val decoder = Base64.getDecoder()
            imageByte = decoder.decode(imageString)
            val bis = ByteArrayInputStream(imageByte)
            image = ImageIO.read(bis)
            bis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return image!!
    }

    fun extract(image: BufferedImage?): BinaryBitmap {
        val bufferedImageLuminanceSource = BufferedImageLuminanceSource(image)
        val huHybridBinarizer = HybridBinarizer(bufferedImageLuminanceSource)
        return BinaryBitmap(huHybridBinarizer)
    }
}