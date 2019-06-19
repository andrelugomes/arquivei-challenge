package br.com.arquivei.etl.invoice.domain.invoice.service;

import br.com.arquivei.etl.invoice.domain.apiclient.InvoiceResponse;
import br.com.arquivei.etl.invoice.domain.invoice.Invoice;
import br.com.arquivei.etl.invoice.domain.invoice.XML;
import br.com.arquivei.etl.invoice.domain.invoice.repository.InvoiceRepository;
import br.com.arquivei.etl.invoice.domain.invoice.xml.NfeProc;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvoiceServiceTest {

  @Autowired
  private InvoiceService service;

  @Autowired
  private InvoiceRepository repository;

  @Test
  public void shouldTransformAsNewNfeProc() {
    final var accessKey = "35180100355382000176550010000079441000079448";

    final var response = new InvoiceResponse();
    response.setAccessKey(accessKey);
    response.setXml(XML.BASE_64); //vNF=6.49

    final NfeProc nfeProc = service.transform(response);

    Assertions.assertThat(nfeProc.getProtNFe().getInfProt().getChNFe()).isEqualTo(accessKey);
    Assertions.assertThat(nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getvNf()).isEqualTo(6.49);

  }

  @Test
  public void shouldSaveANewInvoice() {
    final var accessKey = "35180100355382000176550010000079441000079448";
    final var response = new InvoiceResponse();
    response.setAccessKey(accessKey);
    response.setXml(XML.BASE_64); //vNF=6.49

    final var nfeProc = service.transform(response);

    service.load(nfeProc);

    final var invoice = repository.findById(accessKey).get();

    Assertions.assertThat(invoice).isInstanceOf(Invoice.class);
    Assertions.assertThat(invoice.getAccessKey()).isEqualTo(accessKey);
    Assertions.assertThat(invoice.getValue()).isEqualTo(6.49);

  }

}