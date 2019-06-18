package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class InfNFe {

  public InfNFe() {
  }

  private Total total;
  private String id;

  public Total getTotal() {
    return total;
  }

  @XmlElement(name = "total")
  public void setTotal(final Total total) {
    this.total = total;
  }

  public String getId() {
    return id;
  }

  @XmlAttribute(name = "Id")
  public void setId(final String id) {
    this.id = id;
  }
}
