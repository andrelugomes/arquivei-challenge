package br.com.arquivei.etl.invoice.domain.apiclient;

public class Status {

  private Integer code;
  private String message;

  public Integer getCode() {
    return code;
  }

  public void setCode(final Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder()//
            .append("Status [")//
            .append("code=")//
            .append(code)//
            .append(",message=\"")//
            .append(message).append("\"")//
            .append("]");
    return builder.toString();
  }
}
