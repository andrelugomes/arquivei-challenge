package br.com.arquivei.etl.invoice.domain.apiclient;

public class Page {

  private String next;
  private String previous;

  public String getNext() {
    return next;
  }

  public void setNext(final String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(final String previous) {
    this.previous = previous;
  }


  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder()//
            .append("Page [")//
            .append("next=\"")//
            .append(next).append("\"")//
            .append(",previous=\"")//
            .append(previous).append("\"")//
            .append("]");
    return builder.toString();
  }
}
