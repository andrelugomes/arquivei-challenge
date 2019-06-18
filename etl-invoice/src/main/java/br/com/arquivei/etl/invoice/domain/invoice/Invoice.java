package br.com.arquivei.etl.invoice.domain.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

  @Id
  @Column(name = "access_key", unique = true)
  private String accessKey;

  @Column
  private Double value;

  public Invoice() {
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(final String accessKey) {
    this.accessKey = accessKey;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(final Double value) {
    this.value = value;
  }
}
