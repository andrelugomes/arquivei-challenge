package br.com.arquivei.etl.invoice.domain.invoice.service;

import java.util.Base64;

import javax.xml.bind.JAXBException;

import br.com.arquivei.etl.invoice.domain.apiclient.InvoiceResponse;
import br.com.arquivei.etl.invoice.domain.invoice.Invoice;
import br.com.arquivei.etl.invoice.domain.invoice.helper.UnmarshalHelper;
import br.com.arquivei.etl.invoice.domain.invoice.repository.InvoiceRepository;
import br.com.arquivei.etl.invoice.domain.invoice.xml.NfeProc;
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
  public void load(final NfeProc nfeProc) {
    log.info("load Nfe ID={}", nfeProc.getNFe().getInfNFe().getId());

    try {
      final var entity = new Invoice();
      entity.setAccessKey(nfeProc.getProtNFe().getInfProt().getChNFe());
      entity.setValue(nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getvNf());

      invoiceRepository.save(entity); //idempotency - JPA load before save because using AccessKey as PK
    } catch (final Exception e) {
      log.error("ERROR={}", e.getMessage());
      throw new RuntimeException("Error when saving Invoice.", e);
    }
  }

  public NfeProc transform(final InvoiceResponse invoice) {
    log.info("transform AccessKey={}", invoice.getAccessKey());

    try {
      return UnmarshalHelper.toNfeProc(transformBase64AsXmlString(invoice));
    } catch (final JAXBException e) {
      log.error("ERROR={}", e.getMessage());
      throw new RuntimeException("Error when parsing Invoice.", e);
    }
  }

  private String transformBase64AsXmlString(final InvoiceResponse invoice) {
    return new String(Base64.getDecoder().decode(invoice.getXml()));
  }
}
