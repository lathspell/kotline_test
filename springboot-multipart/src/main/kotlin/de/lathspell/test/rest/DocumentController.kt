package de.lathspell.test.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentController {

    data class Customer(
        val id: String
    )

    @PutMapping("/produce", produces = [APPLICATION_JSON_VALUE])
    fun produceDocuments(): Map<String, Any> {
        val image = byteArrayOf(0x50, 0x4e, 0x47, 0x00, 0x01)
        val customer = Customer("X123")
        return mapOf(
            "customer.txt" to "A long description...",
            "customer.json" to customer,
            "customer2.json" to ObjectMapper().enable(INDENT_OUTPUT).writeValueAsString(customer).toByteArray(),
            "customer.png" to image
        )
    }

    @PutMapping("/produce-base64", produces = [APPLICATION_JSON_VALUE])
    fun produceBase64(): Map<String, ByteArray> {
        return mapOf("customer.txt" to "Mäx Müßtermänn".toByteArray())
    }
}
