package br.com.arquivei.api.domain.invoice.resource

import br.com.arquivei.api.domain.invoice.Invoice
import br.com.arquivei.api.domain.invoice.repository.InvoiceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoices")
class InvoiceResource(var invoiceRepository : InvoiceRepository) {

    @GetMapping
    fun getInvoices(
        @RequestParam(required = false, defaultValue = "0") page :Int,
        @RequestParam(required = false, defaultValue = "10") size :Int
    ) : Page<Invoice> = invoiceRepository.findAll(PageRequest.of(page, size))

    @GetMapping("/{access_key}")
    fun getInvoice(
        @PathVariable(name = "access_key") accessKey : String
    ) : ResponseEntity<Invoice> = ResponseEntity.ok().body(invoiceRepository.findById(accessKey).get())
    
}
