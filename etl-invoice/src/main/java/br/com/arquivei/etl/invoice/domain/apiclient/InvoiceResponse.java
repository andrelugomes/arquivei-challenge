package br.com.arquivei.etl.invoice.domain.apiclient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceResponse {

  @JsonProperty("access_key")
  private String accessKey;
  private String xml;

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(final String accessKey) {
    this.accessKey = accessKey;
  }

  public String getXml() {
    return xml;
  }

  public void setXml(final String xml) {
    this.xml = xml;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder()//
            .append("Invoice [")//
            .append("accessKey=\"")//
            .append(accessKey).append("\"")//
            .append(",xml=\"")//
            .append(xml,0,10).append("\"")//
            .append("]");
    return builder.toString();
  }
}
