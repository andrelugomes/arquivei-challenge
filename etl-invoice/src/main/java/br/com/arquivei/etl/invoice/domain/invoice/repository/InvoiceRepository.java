package br.com.arquivei.etl.invoice.domain.invoice.repository;

import br.com.arquivei.etl.invoice.domain.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository  extends JpaRepository<Invoice, String> {

}
