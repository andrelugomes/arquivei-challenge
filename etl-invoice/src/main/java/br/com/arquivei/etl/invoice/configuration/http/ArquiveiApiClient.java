package br.com.arquivei.etl.invoice.configuration.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ArquiveiApiClient {

  private static final String X_API_ID = "x-api-id";
  private static final String X_API_KEY = "x-api-key";

  @Bean("apiClient")
  public WebClient apiClient(@Value("${arquivei.api.url}") final String url,
          @Value("${arquivei.api.id}") final String id,
          @Value("${arquivei.api.key}") final String key) {
    return WebClient.builder()
            .baseUrl(url)
            .defaultHeader(X_API_ID, id)
            .defaultHeader(X_API_KEY, key)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }
}
