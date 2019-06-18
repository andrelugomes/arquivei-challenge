package br.com.arquivei.etl.invoice.domain.apiclient;

import java.util.List;

public class Response {

  private Status status;
  private List<InvoiceResponse> data;
  private Page page;
  private Integer count;
  private String signature;

  public Status getStatus() {
    return status;
  }

  public void setStatus(final Status status) {
    this.status = status;
  }

  public List<InvoiceResponse> getData() {
    return data;
  }

  public void setData(final List<InvoiceResponse> data) {
    this.data = data;
  }

  public Page getPage() {
    return page;
  }

  public void setPage(final Page page) {
    this.page = page;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(final Integer count) {
    this.count = count;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(final String signature) {
    this.signature = signature;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder()//
            .append("InvoiceResponse [")//
            .append("status=")//
            .append(status)//
            .append(",data=")//
            .append(data)//
            .append(",page=")//
            .append(page)//
            .append(",count=")//
            .append(count)//
            .append(",signature=\"")//
            .append(signature).append("\"")//
            .append("]");
    return builder.toString();
  }
}
