package br.com.arquivei.etl.invoice.domain.etl;

import br.com.arquivei.etl.invoice.domain.apiclient.Response;
import br.com.arquivei.etl.invoice.domain.apiclient.service.WebClientService;
import br.com.arquivei.etl.invoice.domain.etl.helper.CursorHelper;
import br.com.arquivei.etl.invoice.domain.etl.service.CursorControllService;
import br.com.arquivei.etl.invoice.domain.invoice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Profile("!test")
public class ETL implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(ETL.class);

  @Autowired
  private WebClientService webClientService;

  @Autowired
  private CursorControllService cursorControllService;

  @Autowired
  private InvoiceService invoiceService;

  @Autowired
  private ApplicationContext context;

  @Value("${etl.cursor.control.limit}")
  private Integer limit;

  @Override
  public void run(final String... args) {
    log.info("Starting ETL.");
    while (getInvoices()) {
      log.info("Getting invoices...");
    }
    log.info("Finished ETL.");
    SpringApplication.exit(context,() -> 0);
  }

  public boolean getInvoices() {

    final var cursor = cursorControllService.current();

    //Extract
    final var response = webClientService.receive(limit, cursor);

    //Transform
    response.subscribe(res -> {
      log.info("response, {}, Count={}", res.getStatus(), res.getCount());
      if (responseIsOk(res) && hasData(res)) {

        res.getData().forEach(invoice -> {

          //Load
          invoiceService.load(invoice);

        });
        cursorControllService.update(cursor, res.getPage());
      }
    });
    return hasNext(response);
  }

  private boolean hasData(final Response response) {
    return response.getData() != null && !response.getData().isEmpty();
  }

  private boolean responseIsOk(final Response res) {
    return res.getStatus() != null && res.getStatus().getCode() != null && res.getStatus().getCode() == 200;
  }

  private Boolean hasNext(final Mono<Response> response) {
    return response.map(r -> CursorHelper.hasNext(r.getPage(), r.getCount())).block();
  }
}
