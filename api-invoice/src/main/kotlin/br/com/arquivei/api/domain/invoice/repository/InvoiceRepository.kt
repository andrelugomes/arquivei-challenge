package br.com.arquivei.api.domain.invoice.repository

import br.com.arquivei.api.domain.invoice.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<Invoice, String>
