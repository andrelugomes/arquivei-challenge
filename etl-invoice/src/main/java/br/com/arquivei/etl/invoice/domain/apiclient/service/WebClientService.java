package br.com.arquivei.etl.invoice.domain.apiclient.service;

import java.time.Duration;

import br.com.arquivei.etl.invoice.domain.apiclient.Response;
import br.com.arquivei.etl.invoice.domain.etl.CursorControll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

  private static final Logger log = LoggerFactory.getLogger(WebClientService.class);

  public static final String NFE_RECEIVED_PATH = "/v1/nfe/received";
  public static final String CURSOR = "cursor";
  public static final String LIMIT = "limit";

  @Autowired
  @Qualifier("apiClient")
  private WebClient client;

  public Mono<Response> receive(final Integer limit, final CursorControll cursor) {
    log.info("receive, limit={} , cursor={}", limit, cursor.getPosition());
    return client.get()
            .uri(builder -> builder.path(NFE_RECEIVED_PATH)
                    .queryParam(CURSOR, cursor.getPosition())
                    .queryParam(LIMIT, limit)
                    .build())
            .retrieve()
            .bodyToMono(Response.class)
            .retryBackoff(3, Duration.ofMillis(100));
  }
}
