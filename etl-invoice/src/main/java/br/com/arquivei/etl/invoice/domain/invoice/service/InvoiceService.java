package br.com.arquivei.etl.invoice.domain.invoice.service;

import java.util.Base64;

import br.com.arquivei.etl.invoice.domain.apiclient.InvoiceResponse;
import br.com.arquivei.etl.invoice.domain.invoice.Invoice;
import br.com.arquivei.etl.invoice.domain.invoice.helper.UnmarshalHelper;
import br.com.arquivei.etl.invoice.domain.invoice.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService {

  private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

  private InvoiceRepository invoiceRepository;

  public InvoiceService(final InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }

  @Transactional
  public void load(final InvoiceResponse invoice) {
    log.info("load AccessKey={}", invoice.getAccessKey());

    try {
      final var nfeProc = UnmarshalHelper.toNfeProc(getXmlAsString(invoice));

      final var entity = new Invoice();
      entity.setAccessKey(invoice.getAccessKey());
      entity.setValue(nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getvNf());

      invoiceRepository.save(entity); //idempotency - JPA load before save because using AccessKey as PK

    } catch (final Exception e) {
      log.error("ERROR = {}", e.getMessage());
      throw new RuntimeException("Error when saving Invoice.", e);
    }
  }

  private String getXmlAsString(final InvoiceResponse invoice) {
    return new String(Base64.getDecoder().decode(invoice.getXml()));
  }
}
